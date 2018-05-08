package model;

public class Task4Output {
    String ParticipantName = null;
    Integer AOIIndex = -1;
    Long FixationNumber = 0l;
    Long FixationIndex = 0l;
    Long FixationDuration = 0l;
    Long FixationDurationAll = 0l;
    Double FixationDiv = 0d;

    public Task4Output(Point point) {
        if(point.getInAOI1()) {
            this.AOIIndex = 1;
        }

        if(point.getInAOI2()) {
            this.AOIIndex = 2;
        }

        this.ParticipantName = point.getParticipantName().replaceAll("\"", "");
        this.FixationIndex = point.getFixationIndex();
        this.FixationDuration = point.getGazeEventDuration();
    }


    public String getParticipantName() {
        return ParticipantName;
    }

    public void setParticipantName(String participantName) {
        ParticipantName = participantName;
    }

    public Integer getAOIIndex() {
        return AOIIndex;
    }

    public void setAOIIndex(Integer AOIIndex) {
        this.AOIIndex = AOIIndex;
    }

    public Long getFixationNumber() {
        return FixationNumber;
    }

    public void setFixationNumber(Long fixationNumber) {
        FixationNumber = fixationNumber;
    }

    public Long getFixationIndex() {
        return FixationIndex;
    }

    public void setFixationIndex(Long fixationIndex) {
        FixationIndex = fixationIndex;
    }

    public Long getFixationDuration() {
        return FixationDuration;
    }

    public void setFixationDuration(Long fixationDuration) {
        FixationDuration = fixationDuration;
    }

    public Long getFixationDurationAll() {
        return FixationDurationAll;
    }

    public void setFixationDurationAll(Long fixationDurationAll) {
        FixationDurationAll = fixationDurationAll;
    }

    public Double getFixationDiv() {
        return FixationDiv;
    }

    public void setFixationDiv(Double fixationDiv) {
        FixationDiv = fixationDiv;
    }
}
