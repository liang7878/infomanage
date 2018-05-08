import com.monitorjbl.xlsx.StreamingReader;
import model.AOI;
import model.Click;
import model.Point;
import model.Record;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Task1 {

    public static HashMap<String, ArrayList<Point>> resultHashmap = new HashMap<>(5000);
    public static ArrayList<String> deathNote = new ArrayList<>();
    public static HashMap<String, HashMap<Integer, AOI>> finalresult = new HashMap<>();
    public static HashMap<String, ArrayList<Click>> clickResult = new HashMap<>();
    public static ArrayList<AOI> task2result = new ArrayList<>();


    public static void main(String[] args) {
        String outputPath = "c://Users/ShiChengliang/IdeaProjects/EyesControl/doc/task1/output.xlsx";
        System.out.println("Hello World!");
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
            readXlsx("c://Users/ShiChengliang/IdeaProjects/EyesControl/doc/task1/1.xlsx");
        } catch (IOException ioe) {
            System.out.println(ioe);
        }


        for(String key: resultHashmap.keySet()) {
            if(deathNote.contains(key)) continue;
            task2result.add(new AOI(key, resultHashmap.get(key), clickResult.get(key)));
        }

        File file = new File(outputPath);
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet();
        Row titleRow = sheet.createRow(0);
        titleRow.createCell(0).setCellValue("ParticipantName");
        titleRow.createCell(1).setCellValue("AQI-FixationNumber");
        titleRow.createCell(2).setCellValue("Interface-FixationNumber");
        titleRow.createCell(3).setCellValue("Fixation-div");
        titleRow.createCell(4).setCellValue("FirstFixationTime");
        titleRow.createCell(5).setCellValue("FirstClickTime");
        titleRow.createCell(6).setCellValue("Click-FixationTime");
        int index=1;



        for (int i = 0; i < task2result.size(); i++) {
            AOI aoi = task2result.get(i);
            Row record = sheet.createRow(index);
            record.createCell(0).setCellValue(aoi.getParticipantName());
            record.createCell(1).setCellValue(aoi.getAQIFixationNumber());
            record.createCell(2).setCellValue(aoi.getINTERFACEFixationNumber());
            record.createCell(3).setCellValue(aoi.getDiv());
            record.createCell(4).setCellValue(aoi.getFirstFixationTimestamp());
            record.createCell(5).setCellValue(aoi.getFirstClickTimestamp());
            record.createCell(6).setCellValue(aoi.getSub());
            index ++;
        }

        try {
            if(!file.exists()) {
                OutputStream fos = new FileOutputStream(outputPath);
                workbook.write(fos);
                fos.flush();
                fos.close();
            }


            System.out.println("success");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("finish");
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
            if(step >= 500000) return;
            if(row.getRowNum() == 0) {
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

            System.out.println("processing row " + (row.getRowNum()+1));
            //遍历所有的列
            Record record = new Record(row);
            if(record.isLegal()) {
                if(record.getType().equals("click")) {


                    Click click = new Click(record);

                    if(!click.getInAOI1() && !click.getInAQI2()) {

                    } else {
                        if(clickResult.get(record.getParticipantName()) == null) {
                            clickResult.put(record.getParticipantName(), new ArrayList<>());
                        }
                        clickResult.get(record.getParticipantName()).add(new Click(record));
                    }

                } else if(record.getType().equals("Fixation")) {
                    if(point.addRecord(record)){
                        continue;
                    }else {
                        if(resultHashmap.get(point.getParticipantName()) == null) {
                            resultHashmap.put(point.getParticipantName(), new ArrayList<>(1000));
                        }

                        resultHashmap.get(point.getParticipantName()).add(point);
//                    result.add(point);
                        point = new Point();
                        point.addRecord(record);
                    }
                } else if(record.getType().equals("both")) {
                    Click click = new Click(record);

                    if(!click.getInAOI1() && !click.getInAQI2()) {

                    } else {
                        if(clickResult.get(record.getParticipantName()) == null) {
                            clickResult.put(record.getParticipantName(), new ArrayList<>());
                        }
                        clickResult.get(record.getParticipantName()).add(new Click(record));
                    }

                    if(point.addRecord(record)){
                        continue;
                    }else {
                        if(resultHashmap.get(point.getParticipantName()) == null) {
                            resultHashmap.put(point.getParticipantName(), new ArrayList<>(1000));
                        }

                        resultHashmap.get(point.getParticipantName()).add(point);
//                    result.add(point);
                        point = new Point();
                        point.addRecord(record);
                    }
                }
            }

        }

        System.out.println("finish");
    }
}
