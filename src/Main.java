import com.monitorjbl.xlsx.StreamingReader;
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

public class Main {
//
//    public static HashMap<String, ArrayList<Point>> resultHashmap = new HashMap<>(5000);
//    public static ArrayList<String> deathNote = new ArrayList<>();
//
//
//    public static void main(String[] args) {
//        String outputPath = "c://Users/ShiChengliang/IdeaProjects/EyesControl/doc/task1/output.xlsx";
//        System.out.println("Hello World!");
//        deathNote.add("P04邢睿");
//        deathNote.add("P09路雅琦");
//        deathNote.add("P12晏茂源");
//        deathNote.add("P14唐青青");
//        deathNote.add("P17李显鑫");
//        deathNote.add("P18卢泉竹");
//        deathNote.add("P25王俊");
//        deathNote.add("P27柳叶");
//        deathNote.add("P38杨依依");
//
//        try{
//            readXlsx("c://Users/ShiChengliang/IdeaProjects/EyesControl/doc/task1/1.xlsx");
//        } catch (IOException ioe) {
//            System.out.println(ioe);
//        }
//
//
//        File file = new File(outputPath);
//
//
//        Workbook workbook = new XSSFWorkbook();
//
//
//
//        Sheet sheet = workbook.createSheet();
//        Row titleRow = sheet.createRow(0);
//        titleRow.createCell(0).setCellValue("ParticipantName");
//        titleRow.createCell(1).setCellValue("AQIIndex");
//        titleRow.createCell(2).setCellValue("FixationNumber");
//        titleRow.createCell(3).setCellValue("FixationIndex");
//        titleRow.createCell(4).setCellValue("FixationDuration");
//        titleRow.createCell(5).setCellValue("fixationGap");
//        int index=1;
//
//        for(String key : resultHashmap.keySet()) {
//            if(deathNote.contains(key)) {
//                continue;
//            }
//            ArrayList<Point> result = resultHashmap.get(key);
//            for (int i = 0; i < result.size(); i++) {
//                Point point = result.get(i);
//                point.setFixationGap(Math.abs(point.getStopTimestamp())-Math.abs(point.getStartTimestamp()));
//                Row record = sheet.createRow(index);
//                record.createCell(0).setCellValue(point.getParticipantName());
//                record.createCell(1).setCellValue(point.getAQIIndex());
//                record.createCell(2).setCellValue(point.getFixationNumber());
//                record.createCell(3).setCellValue(point.getFixationIndex());
//                record.createCell(4).setCellValue(point.getGazeEventDuration());
//                record.createCell(5).setCellValue(point.getFixationGap());
//                index++;
//            }
//
//        }
//
//        try {
//            if(!file.exists()) {
//                OutputStream fos = new FileOutputStream(outputPath);
//                workbook.write(fos);
//                fos.flush();
//                fos.close();
//            }
//
//
//            System.out.println("success");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("finish");
//    }
//
//    public static void readXlsx(String path) throws IOException {
//        FileInputStream in = new FileInputStream(path);
//        Workbook wk = StreamingReader.builder()
//                .rowCacheSize(100)
//                .bufferSize(4096)
//                .open(in);
//        Sheet sheet = wk.getSheetAt(0);
//        Point point = new Point();
//        //遍历所有的行
//        for (Row row : sheet) {
//            if(row.getRowNum() == 0) {
//                continue;
//            }
//            System.out.println("processing row " + (row.getRowNum()+1));
//            //遍历所有的列
//            Record record = new Record(row);
//            if(record.isLegal()) {
//                if(point.addRecord(record)){
//                    continue;
//                }else {
//                    if(resultHashmap.get(point.getParticipantName()) == null) {
//                        resultHashmap.put(point.getParticipantName(), new ArrayList<>(1000));
//                    }
//
//
//
//                    resultHashmap.get(point.getParticipantName()).add(point);
////                    result.add(point);
//                    point = new Point();
//                    point.addRecord(record);
//                }
//            }
//
//            if(row.getRowNum() == 187130) {
//                System.out.println("break");
//            }
//        }
//
//        System.out.println("finish");
//    }
}
