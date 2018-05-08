package model;

import java.util.ArrayList;

public class AOI {
    String ParticipantName = null;
    Long AQIFixationNumber = 0l;
    Long INTERFACEFixationNumber = 0l;
    Double div = 0d;
    Long firstFixationTimestamp = 0l;
    Long firstClickTimestamp = 0l;
    Long sub = 0l;


    public AOI(String name, ArrayList<Point> points, ArrayList<Click> clicks) {
        this.ParticipantName = name;

        Click click = null;
        Point firstPoint = null;
        if(points == null || points.size() == 0) {
            if(clicks == null || clicks.size() == 0) {
                this.AQIFixationNumber = -1l;
                this.INTERFACEFixationNumber = -1l;
                this.div = -1d;
                this.firstFixationTimestamp = -1l;
                this.firstClickTimestamp = -1l;
                this.sub = -1l;
            } else {
                for (int i = 0; i < points.size(); i++) {
                    Point tmp = points.get(i);
                    if(tmp.getInAOI1()) {
                        this.INTERFACEFixationNumber +=1;
                    }
                    if(tmp.getInAOI2()) {
                        this.AQIFixationNumber +=1;
                        if(firstPoint == null) {
                            firstPoint = tmp;
                        } else {
                            if(firstPoint.getStartTimestamp() > tmp.getStartTimestamp()) {
                                firstPoint =tmp;
                            }
                        }
                    }
                }
            }
        } else if(clicks == null || clicks.size() == 0) {
            if(points == null || points.size() == 0) {
                this.AQIFixationNumber = -1l;
                this.INTERFACEFixationNumber = -1l;
                this.div = -1d;
                this.firstFixationTimestamp = -1l;
                this.firstClickTimestamp = -1l;
                this.sub = -1l;
            } else {
                for (int i = 0; i < points.size(); i++) {
                    Point tmp = points.get(i);
                    if(tmp.getInAOI1()) {
                        this.INTERFACEFixationNumber +=1;
                    }
                    if(tmp.getInAOI2()) {
                        this.AQIFixationNumber +=1;
                        if(firstPoint == null) {
                            firstPoint = tmp;
                        } else {
                            if(firstPoint.getStartTimestamp() > tmp.getStartTimestamp()) {
                                firstPoint =tmp;
                            }
                        }
                    }
                }
            }
        } else {

            for (int i = 0; i < clicks.size(); i++) {

                if(clicks.get(i).getInAQI2()) {
                    if(click==null){
                        click = clicks.get(i);
                    } else {
                        if(click.getEyeTrackerTimestamp() > clicks.get(i).getEyeTrackerTimestamp()) {
                            click = clicks.get(i);
                        }
                    }
                }
            }

            for (int i = 0; i < points.size(); i++) {
                Point tmp = points.get(i);
                if(tmp.getInAOI1()) {
                    this.INTERFACEFixationNumber +=1;
                }
                if(tmp.getInAOI2()) {
                    this.AQIFixationNumber +=1;
                    if(firstPoint == null) {
                        firstPoint = tmp;
                    } else {
                        if(firstPoint.getStartTimestamp() > tmp.getStartTimestamp()) {
                            firstPoint =tmp;
                        }
                    }
                }
            }
        }

        if(click == null) {
            this.firstClickTimestamp = -1l;
        } else {
            this.firstClickTimestamp = click.getEyeTrackerTimestamp();
        }
        if(firstPoint == null) {
            this.firstFixationTimestamp = -1l;
        } else {
            this.firstFixationTimestamp = firstPoint.getStartTimestamp();
        }

        if(click !=null && firstPoint!=null) {
            this.sub = click.getEyeTrackerTimestamp() - firstPoint.getStartTimestamp();
        } else {
            this.sub = -1l;
        }

        if(this.INTERFACEFixationNumber != 0) {
            this.div = Double.valueOf(String.valueOf(this.AQIFixationNumber))/Double.valueOf(String.valueOf(this.INTERFACEFixationNumber));
        } else {
            this.div = -1d;
        }

    }

    public String getParticipantName() {
        return ParticipantName;
    }

    public void setParticipantName(String participantName) {
        ParticipantName = participantName;
    }

    public Long getAQIFixationNumber() {
        return AQIFixationNumber;
    }

    public void setAQIFixationNumber(Long AQIFixationNumber) {
        this.AQIFixationNumber = AQIFixationNumber;
    }

    public Long getINTERFACEFixationNumber() {
        return INTERFACEFixationNumber;
    }

    public void setINTERFACEFixationNumber(Long INTERFACEFixationNumber) {
        this.INTERFACEFixationNumber = INTERFACEFixationNumber;
    }

    public Double getDiv() {
        return div;
    }

    public void setDiv(Double div) {
        this.div = div;
    }

    public Long getFirstFixationTimestamp() {
        return firstFixationTimestamp;
    }

    public void setFirstFixationTimestamp(Long firstFixationTimestamp) {
        this.firstFixationTimestamp = firstFixationTimestamp;
    }

    public Long getFirstClickTimestamp() {
        return firstClickTimestamp;
    }

    public void setFirstClickTimestamp(Long firstClickTimestamp) {
        this.firstClickTimestamp = firstClickTimestamp;
    }

    public Long getSub() {
        return sub;
    }

    public void setSub(Long sub) {
        this.sub = sub;
    }
}
