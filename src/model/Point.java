package model;

import org.apache.poi.ss.usermodel.Row;

public class Point {
    private boolean isEmpty = true;
    Long FixationNumber = 0l;
    String ParticipantName = null;
    Long EyeTrackerTimestamp = null;
    Long FixationIndex = null;
    String GazeEventType = null;
    Long GazeEventDuration = 0l;
    String FixationPointX_MCSpx = null;
    String FixationPointY_MCSpx = null;
    Integer AQIIndex = -1;
    Long startTimestamp = 0l;
    Long stopTimestamp = 0l;
    Long fixationGap = 0l;
    Boolean inAOI1 = false;
    Boolean inAOI2 = false;
    public Point() {

    }

    public boolean addRecord(Record record) {
        if(!record.GazeEventType.equals("Fixation")) {
            return false;
        }
        if(this.ParticipantName == null) {
            this.ParticipantName = record.ParticipantName;
            this.startTimestamp = record.getEyeTrackerTimestamp();
            this.AQIIndex = getAQI(record);
        } else if(!this.ParticipantName.equals(record.ParticipantName)) {
            return false;
        }
        if(this.FixationIndex == null) {
            this.FixationIndex = record.FixationIndex;
        } else if(!this.FixationIndex.equals(record.FixationIndex)) {
            return false;
        }

        this.FixationNumber += 1;
        this.GazeEventDuration += record.getGazeEventDuration();

        if(this.startTimestamp.equals(Long.MIN_VALUE)) {
            this.startTimestamp = record.getEyeTrackerTimestamp();
        }

        if(record.getEyeTrackerTimestamp().equals(Long.MIN_VALUE)) {

        } else {
            this.setStopTimestamp(record.getEyeTrackerTimestamp());
        }

        Integer x = record.getFixationPointX_MCSpx();
        Integer y = record.getFixationPointY_MCSpx();

        if(x>1.56 && x<1918.42 && y>1246.11 && y<3430.33) {
            this.inAOI1 = true;
        }

        //in aoi2?
        if(x>395.57 &&x<1530.67 && y>3325.57 && y<3403.75) {
            this.inAOI2 = true;
        }



        return true;
    }

    private int getAQI(Record record) {
        int result = 1;
        Integer x = record.getFixationPointX_MCSpx();
        Integer y = record.getFixationPointY_MCSpx();

        if(x>349.38 && x<536.28 && y<50.97 && y>-1.06) {
            return 14;
        }

        if(x>1034.34 && x<1569.56 &&y>0 &&y<54.16) {
            return 15;
        }

        if(x>0 && x<1918.42 && y>53.16 && y<950.61) {
            return 16;
        }

        if(x>639.47 && x<1296.14 && y>700.45 && y<781.75) {
            return 17;
        }

        if(x>-1.56 && x<1918.42 && y>952.17 && y<1096.02) {
            return 18;
        }

        if(x>1.56 && x<1918.42 && y>1246.11 && y<3430.33) {
            return 19;
        }

        if(x>4.69 && x<1918.42 && y>3435.02 && y<6860.66) {
            return 20;
        }

        if(x>4.69 && x<1916.86 && y>6865.35 && y<7326.58) {
            return 21;
        }

        return -1;
    }

    public String getParticipantName() {
        return ParticipantName;
    }

    public void setParticipantName(String participantName) {
        ParticipantName = participantName;
    }

    public Long getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Long startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public Long getStopTimestamp() {
        return stopTimestamp;
    }

    public void setStopTimestamp(Long stopTimestamp) {
        this.stopTimestamp = stopTimestamp;
    }

    public Long getFixationGap() {
        return fixationGap;
    }

    public void setFixationGap(Long fixationGap) {
        this.fixationGap = fixationGap;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public Long getFixationNumber() {
        return FixationNumber;
    }

    public void setFixationNumber(Long fixationNumber) {
        FixationNumber = fixationNumber;
    }

    public Long getEyeTrackerTimestamp() {
        return EyeTrackerTimestamp;
    }

    public void setEyeTrackerTimestamp(Long eyeTrackerTimestamp) {
        EyeTrackerTimestamp = eyeTrackerTimestamp;
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

    public Long getGazeEventDuration() {
        return GazeEventDuration;
    }

    public void setGazeEventDuration(Long gazeEventDuration) {
        GazeEventDuration = gazeEventDuration;
    }

    public String getFixationPointX_MCSpx() {
        return FixationPointX_MCSpx;
    }

    public void setFixationPointX_MCSpx(String fixationPointX_MCSpx) {
        FixationPointX_MCSpx = fixationPointX_MCSpx;
    }

    public String getFixationPointY_MCSpx() {
        return FixationPointY_MCSpx;
    }

    public void setFixationPointY_MCSpx(String fixationPointY_MCSpx) {
        FixationPointY_MCSpx = fixationPointY_MCSpx;
    }

    public Integer getAQIIndex() {
        return AQIIndex;
    }

    public void setAQIIndex(Integer AQIIndex) {
        this.AQIIndex = AQIIndex;
    }

    public Boolean getInAOI1() {
        return inAOI1;
    }

    public void setInAOI1(Boolean inAOI1) {
        this.inAOI1 = inAOI1;
    }

    public Boolean getInAOI2() {
        return inAOI2;
    }

    public void setInAOI2(Boolean inAOI2) {
        this.inAOI2 = inAOI2;
    }
}
