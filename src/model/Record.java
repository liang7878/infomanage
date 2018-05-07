package model;

import org.apache.poi.ss.usermodel.Row;

public class Record {
    boolean isLegal = false;
    String type = "F";
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
        if(row.getCell(44) != null && !row.getCell(44).getStringCellValue().equals("Fixation")) {
            this.isLegal = false;
        }else {
            this.GazeEventType = row.getCell(44).getStringCellValue();
            this.FixationIndex = Long.valueOf(row.getCell(42).getStringCellValue());
            this.ParticipantName = row.getCell(4).getStringCellValue();
            if(!row.getCell(26).getStringCellValue().equals("")) {
                this.EyeTrackerTimestamp = Long.valueOf(row.getCell(26).getStringCellValue());
            } else {
                this.EyeTrackerTimestamp = Long.MIN_VALUE;
            }

            if(!row.getCell(45).getStringCellValue().equals("")) {
                this.GazeEventDuration = Long.valueOf(row.getCell(45).getStringCellValue());
            } else {
                this.GazeEventDuration = 0l;
            }

            this.FixationNumber = this.FixationIndex;
            if(row.getCell(46).getStringCellValue().equals("")) {
                this.FixationPointX_MCSpx = Integer.MIN_VALUE;
            } else {
                this.FixationPointX_MCSpx = Integer.valueOf(row.getCell(46).getStringCellValue());
            }

            if(row.getCell(47).getStringCellValue().equals("")) {
                this.FixationPointY_MCSpx = Integer.MIN_VALUE;
            } else {
                this.FixationPointY_MCSpx = Integer.valueOf(row.getCell(47).getStringCellValue());
            }



//            this.FixationPointX_MCSpx = Integer.valueOf(row.getCell(46).getStringCellValue());
//            this.FixationPointY_MCSpx = Integer.valueOf(row.getCell(47).getStringCellValue());
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
}










