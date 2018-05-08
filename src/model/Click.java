package model;

import org.apache.poi.ss.usermodel.Row;

public class Click {
    String ParticipantName = null;
    Long EyeTrackerTimestamp = null;
    Boolean inAOI1 = false;
    Boolean inAQI2 = false;

    public Click(Record record) {
        this.ParticipantName = record.getParticipantName();
        this.EyeTrackerTimestamp = record.getEyeTrackerTimestamp();
        int x = record.getClickX_MCSpx();
        int y = record.getClickY_MCSpx();

        //in aoi1?
        if(x>1.56 && x<1918.42 && y>1246.11 && y<3430.33) {
            this.inAOI1 = true;
        }

        //in aoi2?
        if(x>395.57 &&x<1530.67 && y>3325.57 && y<3403.75) {
            this.inAQI2 = true;
        }
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

        return -1;
    }


    public String getParticipantName() {
        return ParticipantName;
    }

    public void setParticipantName(String participantName) {
        ParticipantName = participantName;
    }

    public Long getEyeTrackerTimestamp() {
        return EyeTrackerTimestamp;
    }

    public void setEyeTrackerTimestamp(Long eyeTrackerTimestamp) {
        EyeTrackerTimestamp = eyeTrackerTimestamp;
    }

    public Boolean getInAOI1() {
        return inAOI1;
    }

    public void setInAOI1(Boolean inAOI1) {
        this.inAOI1 = inAOI1;
    }

    public Boolean getInAQI2() {
        return inAQI2;
    }

    public void setInAQI2(Boolean inAQI2) {
        this.inAQI2 = inAQI2;
    }
}

