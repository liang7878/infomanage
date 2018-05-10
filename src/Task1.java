import com.monitorjbl.xlsx.StreamingReader;
import model.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Task1 {

    public static HashMap<String, ArrayList<Record>> resultHashmap = new HashMap<>(5000);
    public static ArrayList<String> deathNote = new ArrayList<>();
    public static ArrayList<Saccade> task3result = new ArrayList<>(2000000);


    public static void main(String[] args) {
        String outputPath = "c://Users/ShiChengliang/IdeaProjects/EyesControl/doc/task1/output.xlsx";
        deathNote.add("P04邢睿");
        deathNote.add("P09路雅琦");
        deathNote.add("P12晏茂源");
        deathNote.add("P14唐青青");
        deathNote.add("P17李显鑫");
        deathNote.add("P18卢泉竹");
        deathNote.add("P25王俊");
        deathNote.add("P27柳叶");
        deathNote.add("P38杨依依");

        try{
            ArrayList<String> paths = getFilenameList("c://Users/ShiChengliang/IdeaProjects/EyesControl/doc/task3/");
            for (int i = 0; i < paths.size(); i++) {
                System.out.println("processing " + paths.get(i));
                resultHashmap = new HashMap<>();
                readXlsx("c://Users/ShiChengliang/IdeaProjects/EyesControl/doc/task3/"+ paths.get(i));

                for(String key: resultHashmap.keySet()) {
                    if(key.equals("P05刘顺")) {
                        System.out.println("break");
                    }
                    if(deathNote.contains(key)) continue;

                    ArrayList<Record> slist = resultHashmap.get(key);
                    ArrayList<Saccade> tmp = new ArrayList<>();

                    for (int j = 0; j < slist.size(); j++) {
                        Record pri;
                        Record current = slist.get(j);
                        if(j == 0) {
                            pri = null;
                        }else {
                            pri = slist.get(j-1);
                        }
                        tmp.add(new Saccade(pri, current));
                    }

                    Double saccadeLengthAll = 0d;
                    for (int j = 0; j < tmp.size(); j++) {
                        saccadeLengthAll+= tmp.get(j).getSaccadeLength();
                    }

                    for (int j = 0; j < tmp.size(); j++) {
                        tmp.get(j).setSaccadeLengthAll(saccadeLengthAll);
                        tmp.get(j).setSaccadeNumber((long) tmp.size());
                        tmp.get(j).setSaccadeLengthAverage(saccadeLengthAll/tmp.size());
                    }

                    task3result.addAll(tmp);
                }
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }


        File file = new File(outputPath);
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet();
        Row titleRow = sheet.createRow(0);
        titleRow.createCell(0).setCellValue("ParticipantName");
        titleRow.createCell(1).setCellValue("SaccadeNumber");
        titleRow.createCell(2).setCellValue("SaccadeIndex");
        titleRow.createCell(3).setCellValue("RelativeSaccadeDirection");
        titleRow.createCell(4).setCellValue("SaccadeLength");
        titleRow.createCell(5).setCellValue("SaccadeLengthAverage");
        titleRow.createCell(6).setCellValue("SaccadeLengthAll");
        titleRow.createCell(7).setCellValue("MediaName");
        int index=1;

        for (int k = 0; k < task3result.size(); k++) {
            Saccade aoi = task3result.get(k);
            Row record = sheet.createRow(index);
            record.createCell(0).setCellValue(aoi.getParticipantName());
            record.createCell(1).setCellValue(aoi.getSaccadeNumber());
            record.createCell(2).setCellValue(aoi.getSaccadeIndex());
            record.createCell(3).setCellValue(aoi.getRelativeSaccadeDirection());
            record.createCell(4).setCellValue(aoi.getSaccadeLength());
            record.createCell(5).setCellValue(aoi.getSaccadeLengthAverage());
            record.createCell(6).setCellValue(aoi.getSaccadeLengthAll());
            record.createCell(7).setCellValue(aoi.getMediaName());
            index ++;
        }

        try {
            if(!file.exists()) {
                OutputStream fos = new FileOutputStream(outputPath);
                workbook.write(fos);
                fos.flush();
                fos.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("finish");
    }

    private static void processFile() {

    }

    public static void readXlsx(String path) throws IOException {
        FileInputStream in = new FileInputStream(path);
        Workbook wk = StreamingReader.builder()
                .rowCacheSize(100)
                .bufferSize(4096)
                .open(in);
        Sheet sheet = wk.getSheetAt(0);
        Point point = new Point();
        Long step = 0l;
        //遍历所有的行
        for (Row row : sheet) {
            step++;
            if(step >= 11836) return;
            if(row.getRowNum() == 0 || row.getPhysicalNumberOfCells() == 0) {
                continue;
            }
//            if(row.getRowNum() == 53352 ||
//                    row.getRowNum() == 166637 ||
//                    row.getRowNum() == 357171 ||
//                    row.getRowNum() == 357571 ||
//                    row.getRowNum() == 357919 ||
//                    row.getRowNum() == 358287 ||
//                    row.getRowNum() == 359819 ||
//                    row.getRowNum() == 387803 ||
//                    row.getRowNum() == 391851 ||
//                    row.getRowNum() ==  394292||
//                    row.getRowNum() ==  395001||
//                    row.getRowNum() == 396437 ||
//                    row.getRowNum() == 397601 ||
//                    row.getRowNum() == 399409 ||
//                    row.getRowNum() == 399785 ||
//                    row.getRowNum() == 434744) {
//                continue;
//            }

//            System.out.println("processing row " + (row.getRowNum()+1));
            //遍历所有的列
            Record record = new Record(row);
            if(record.isLegal()) {
                if(resultHashmap.get(record.getParticipantName()) == null) {
                    resultHashmap.put(record.getParticipantName(), new ArrayList<>(1000));
                }

                resultHashmap.get(record.getParticipantName()).add(record);
//                    result.add(point);
            }

        }

        System.out.println("finish");
    }

    public static ArrayList<String> getFilenameList(String path) {
        ArrayList<String> result = new ArrayList<>();
        File dir = new File(path);

        if(!dir.exists()) {
            System.out.println("Path Error!");
            return null;
        }

        if(!dir.isDirectory()) {
            System.out.println("Not a Dir");
            return null;
        }

        String[] list = dir.list();
        for (int i = 0; i < list.length; i++) {
            result.add(list[i]);
        }

        return result;
    }
}
