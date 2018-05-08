package model;

import org.apache.poi.ss.usermodel.Row;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Record {
    boolean isLegal = false;
    String type = "Fixation";
    Long FixationNumber = 0l;
    //    String ExportDate = null;
//    String StudioVersionRec = null;
//    String StudioProjectName = null;
//    String StudioTestName = null;
    String ParticipantName = null;
//    String RecordingName = null;
//    String RecordingDate = null;
//    String RecordingDuration = null;
//    String RecordingResolution = null;
//    String PresentationSequence = null;
//    String FixationFilter = null;
//    String MediaName = null;
//    String MediaPosX = null;
//    String MediaPosY = null;
//    String MediaWidth = null;
//    String MediaHeight = null;
//    String SegmentName = null;
//    String SegmentStart = null;
//    String SegmentEnd = null;
//    String SegmentDuration = null;
//    String SceneName = null;
//    String SceneSegmentStart = null;
//    String SceneSegmentEnd = null;
//    String SceneSegmentDuration = null;
//    String RecordingTimestamp = null;
//    String LocalTimeStamp = null;
    Long EyeTrackerTimestamp = null;

//    String MouseEventIndex = null;
//    String MouseEvent = null;
//    String MouseEventX_ADCSpx = null;
//    String MouseEventY_ADCSpx = null;
//    String MouseEventX_MCSpx = null;
//    String MouseEventY_MCSpx = null;
//    String KeyPressEventIndex = null;
//    String KeyPressEvent = null;
//    String StudioEventIndex = null;
//    String StudioEvent = null;
//    String StudioEventData = null;
//    String ExternalEventIndex = null;
//    String ExternalEvent = null;
//    String ExternalEventValue = null;
//    String EventMarkerValue = null;
    Long FixationIndex = null;
//    String SaccadeIndex = null;
    String GazeEventType = null;
    Long GazeEventDuration = null;
    Integer FixationPointX_MCSpx = null;
    Integer FixationPointY_MCSpx = null;
    Integer ClickX_MCSpx = null;
    Integer ClickY_MCSpx = null;
//    String SaccadicAmplitude = null;
//    String AbsoluteSaccadicDirection = null;
//    String RelativeSaccadicDirection = null;
//    String GazePointIndex = null;
//    String GazePointLeftX_ADCSpx = null;
//    String GazePointLeftY_ADCSpx = null;
//    String GazePointRightX_ADCSpx = null;
//    String GazePointRightY_ADCSpx = null;
//    String GazePointX_ADCSpx = null;
//    String GazePointY_ADCSpx = null;
//    String GazePointX_MCSpx = null;
//    String GazePointY_MCSpx = null;
//    String GazePointLeftX_ADCSmm = null;
//    String GazePointLeftY_ADCSmm = null;
//    String GazePointRightX_ADCSmm = null;
//    String GazePointRightY_ADCSmm = null;
//    String StrictAverageGazePointX_ADCSmm = null;
//    String StrictAverageGazePointY_ADCSmm = null;
//    String EyePosLeftX_ADCSmm = null;
//    String EyePosLeftY_ADCSmm = null;
//    String EyePosLeftZ_ADCSmm = null;
//    String EyePosRightX_ADCSmm = null;
//    String EyePosRightY_ADCSmm = null;
//    String EyePosRightZ_ADCSmm = null;
//    String CamLeftX = null;
//    String CamLeftY = null;
//    String CamRightX = null;
//    String CamRightY = null;
//    String DistanceLeft = null;
//    String DistanceRight = null;
//    String PupilLeft = null;
//    String PupilRight = null;
//    String ValidityLeft = null;
//    String ValidityRight = null;
//    String IRMarkerCount = null;
//    String IRMarkerID = null;
//    String PupilGlassesRight = null;

    public Record(Row row) {
//        if(row.getCell(28) != null && !row.getCell(28).getStringCellValue().equals("")) {
//            this.type = "click";
//            if(row.getCell(31).getStringCellValue().equals("") || row.getCell(32).getStringCellValue().equals("")) {
//                this.ClickX_MCSpx = -1;
//                this.ClickY_MCSpx = -1;
//            } else {
//                this.ClickX_MCSpx = Integer.valueOf(row.getCell(31).getStringCellValue());
//                this.ClickY_MCSpx = Integer.valueOf(row.getCell(32).getStringCellValue());
//            }
//
//            this.ParticipantName = row.getCell(4).getStringCellValue();
//
//            this.EyeTrackerTimestamp = getTimestamp(row.getCell(6).getStringCellValue(), row.getCell(25).getStringCellValue());
//
//            this.isLegal = true;
//        }

        if(this.type.equals("click") && row.getCell(44).getStringCellValue().equals("Fixation")) {
            this.type = "both";
        }

        if(row.getCell(28).getStringCellValue().equals("") && !row.getCell(44).getStringCellValue().equals("Fixation")) {
            this.isLegal =false;
        } else if(row.getCell(44).getStringCellValue().replaceAll("\"", "").equals("Fixation")) {
            this.GazeEventType = row.getCell(44).getStringCellValue().replaceAll("\"", "");
            this.FixationIndex = Long.valueOf(row.getCell(42).getStringCellValue());
            this.ParticipantName = row.getCell(4).getStringCellValue();

            this.EyeTrackerTimestamp = getTimestamp(row.getCell(6).getStringCellValue(), row.getCell(25).getStringCellValue());


            if(!row.getCell(45).getStringCellValue().equals("")) {
                this.GazeEventDuration = Long.valueOf(row.getCell(45).getStringCellValue());
            } else {
                this.GazeEventDuration = 0l;
            }

            this.FixationNumber = this.FixationIndex;
            if(row.getCell(46).getStringCellValue().equals("")) {
                this.FixationPointX_MCSpx = Integer.MIN_VALUE;
            } else {
                this.FixationPointX_MCSpx = Integer.valueOf(row.getCell(46).getStringCellValue().replaceAll("\"", ""));
            }

            if(row.getCell(47).getStringCellValue().equals("")) {
                this.FixationPointY_MCSpx = Integer.MIN_VALUE;
            } else {
                this.FixationPointY_MCSpx = Integer.valueOf(row.getCell(47).getStringCellValue().replaceAll("\"", ""));
            }



//            this.FixationPointX_MCSpx = Integer.valueOf(row.getCell(46).getStringCellValue());
//            this.FixationPointY_MCSpx = Integer.valueOf(row.getCell(47).getStringCellValue());
            this.isLegal = true;
        } else if(!row.getCell(28).getStringCellValue().equals("")) {
            this.isLegal = true;
        }
    }


    public Integer getFixationPointX_MCSpx() {
        return FixationPointX_MCSpx;
    }

    public void setFixationPointX_MCSpx(Integer fixationPointX_MCSpx) {
        FixationPointX_MCSpx = fixationPointX_MCSpx;
    }

    public Integer getFixationPointY_MCSpx() {
        return FixationPointY_MCSpx;
    }

    public void setFixationPointY_MCSpx(Integer fixationPointY_MCSpx) {
        FixationPointY_MCSpx = fixationPointY_MCSpx;
    }

    public Long getEyeTrackerTimestamp() {
        return EyeTrackerTimestamp;
    }

    public void setEyeTrackerTimestamp(Long eyeTrackerTimestamp) {
        EyeTrackerTimestamp = eyeTrackerTimestamp;
    }

    public Long getGazeEventDuration() {
        return GazeEventDuration;
    }

    public void setGazeEventDuration(Long gazeEventDuration) {
        GazeEventDuration = gazeEventDuration;
    }

    public boolean isLegal() {
        return isLegal;
    }

    public void setLegal(boolean legal) {
        isLegal = legal;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getFixationNumber() {
        return FixationNumber;
    }

    public void setFixationNumber(Long fixationNumber) {
        FixationNumber = fixationNumber;
    }

    public String getParticipantName() {
        return ParticipantName;
    }

    public void setParticipantName(String participantName) {
        ParticipantName = participantName;
    }

    public Long getFixationIndex() {
        return FixationIndex;
    }

    public void setFixationIndex(Long fixationIndex) {
        FixationIndex = fixationIndex;
    }

    public String getGazeEventType() {
        return GazeEventType;
    }

    public void setGazeEventType(String gazeEventType) {
        GazeEventType = gazeEventType;
    }

    public Integer getClickX_MCSpx() {
        return ClickX_MCSpx;
    }

    public void setClickX_MCSpx(Integer clickX_MCSpx) {
        ClickX_MCSpx = clickX_MCSpx;
    }

    public Integer getClickY_MCSpx() {
        return ClickY_MCSpx;
    }

    public void setClickY_MCSpx(Integer clickY_MCSpx) {
        ClickY_MCSpx = clickY_MCSpx;
    }



    public long getTimestamp(String dateStr, String timeStr) {
        String format = "\"yyyy/MM/dd\" \"HH:mm:ss.SSS\"";

        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try {
            date = formatter.parse(dateStr + " " + timeStr);
            return date.getTime();
        } catch (ParseException e) {

            e.printStackTrace();
            return 0l;
        }

    }
}










