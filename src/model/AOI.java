package model;

public class AOI {
    private boolean isEmpty = true;
    String ParticipantName = null;
    Integer AQIIndex = -1;
    Long FixationNumber = 0l;
    Long GazeEventDuration = 0l;
    Long fixationGap = 0l;


    public boolean addPoint(Point point) {
        boolean result = false;
        if(this.isEmpty) {
            this.ParticipantName = point.getParticipantName();
            this.AQIIndex = point.getAQIIndex();
            this.FixationNumber = 1l;
            this.GazeEventDuration = point.getGazeEventDuration();
            this.fixationGap = Math.abs(point.getStopTimestamp())-Math.abs(point.getStartTimestamp());
            this.isEmpty = false;
            return true;
        } else {
            if(this.ParticipantName.equals(point.getParticipantName()) && this.AQIIndex.equals(point.getAQIIndex())) {
                this.FixationNumber += 1;
                this.GazeEventDuration += point.getGazeEventDuration();
                this.fixationGap += (Math.abs(point.getStopTimestamp())-Math.abs(point.getStartTimestamp()));
            }
        }
        return result;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public String getParticipantName() {
        return ParticipantName;
    }

    public void setParticipantName(String participantName) {
        ParticipantName = participantName;
    }

    public Integer getAQIIndex() {
        return AQIIndex;
    }

    public void setAQIIndex(Integer AQIIndex) {
        this.AQIIndex = AQIIndex;
    }

    public Long getFixationNumber() {
        return FixationNumber;
    }

    public void setFixationNumber(Long fixationNumber) {
        FixationNumber = fixationNumber;
    }

    public Long getGazeEventDuration() {
        return GazeEventDuration;
    }

    public void setGazeEventDuration(Long gazeEventDuration) {
        GazeEventDuration = gazeEventDuration;
    }

    public Long getFixationGap() {
        return fixationGap;
    }

    public void setFixationGap(Long fixationGap) {
        this.fixationGap = fixationGap;
    }
}
