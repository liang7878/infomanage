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

    public static HashMap<String, ArrayList<Point>> resultHashmap = new HashMap<>(5000);
    public static ArrayList<String> deathNote = new ArrayList<>();
    public static HashMap<String, HashMap<Integer, AOI>> finalresult = new HashMap<>();
    public static HashMap<String, ArrayList<Click>> clickResult = new HashMap<>();
    public static ArrayList<AOI> task2result = new ArrayList<>();
    public static ArrayList<Task4Output> task4Outputs = new ArrayList<>();


    public static void main(String[] args) {
        String outputPath = "c://Users/ShiChengliang/IdeaProjects/EyesControl/doc/task1/任务四v2.xlsx";
        System.out.println("Hello World!");
        deathNote.add("P04邢睿");
        deathNote.add("P07陈宇澎");
        deathNote.add("P12晏茂源");
        deathNote.add("P18卢泉竹");
        deathNote.add("P16毕玉涵");
        deathNote.add("P13何婵");
        deathNote.add("P26舒成祥");
        deathNote.add("P23张亮");
        deathNote.add("P38杨依依");

        try{
            readXlsx("c://Users/ShiChengliang/IdeaProjects/EyesControl/doc/task1/4.xlsx");
        } catch (IOException ioe) {
            System.out.println(ioe);
        }


        for(String key: resultHashmap.keySet()) {
            if(deathNote.contains(key)) continue;
            ArrayList<Task4Output> tmpResult = new ArrayList<>();
            ArrayList<Point> tmp = resultHashmap.get(key);
            long AQI1Duration =0l;
            long AQI2Duration =0l;
            long fixationNo1 = 0l;
            long fixationNo2 = 0l;
            for (int i = 0; i < tmp.size(); i++) {
                Point point = tmp.get(i);
                if(point.getInAOI1()) {
                    fixationNo1++;
                    AQI1Duration += point.getGazeEventDuration();
                }
                if(point.getInAOI2()) {
                    fixationNo2++;
                    AQI2Duration += point.getGazeEventDuration();
                }
                Task4Output task4Output = new Task4Output(point);
                if(task4Output.getAOIIndex() != -1) {
                    tmpResult.add(new Task4Output(point));
                }
            }

            for (int i = 0; i < tmpResult.size(); i++) {
                if(tmpResult.get(i).getAOIIndex() == 1) {
                    tmpResult.get(i).setFixationDurationAll(AQI1Duration);
                    tmpResult.get(i).setFixationNumber(fixationNo1);
                }
                if(tmpResult.get(i).getAOIIndex() == 2) {
                    tmpResult.get(i).setFixationDurationAll(AQI2Duration);
                    tmpResult.get(i).setFixationNumber(fixationNo2);
                }

                tmpResult.get(i).setFixationDiv(Double.valueOf(String.valueOf(fixationNo2))/Double.valueOf(String.valueOf(fixationNo1)));
            }
            task4Outputs.addAll(tmpResult);
        }

        File file = new File(outputPath);
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet();
        Row titleRow = sheet.createRow(0);
        titleRow.createCell(0).setCellValue("ParticipantName");
        titleRow.createCell(1).setCellValue("AOIIndex");
        titleRow.createCell(2).setCellValue("FixationNumber");
        titleRow.createCell(3).setCellValue("FixationIndex");
        titleRow.createCell(4).setCellValue("FixationDuration");
        titleRow.createCell(5).setCellValue("FixationDurationAll");
        titleRow.createCell(6).setCellValue("FixationDiv");
        int index=1;



        for (int i = 0; i < task4Outputs.size(); i++) {
            Task4Output aoi = task4Outputs.get(i);
            Row record = sheet.createRow(index);
            record.createCell(0).setCellValue(aoi.getParticipantName());
            record.createCell(1).setCellValue(aoi.getAOIIndex());
            record.createCell(2).setCellValue(aoi.getFixationNumber());
            record.createCell(3).setCellValue(aoi.getFixationIndex());
            record.createCell(4).setCellValue(aoi.getFixationDuration());
            record.createCell(5).setCellValue(aoi.getFixationDurationAll());
            record.createCell(6).setCellValue(aoi.getFixationDiv());
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
            if(step >= 11836) return;
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
