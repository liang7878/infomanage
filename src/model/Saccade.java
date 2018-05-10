package model;

public class Saccade {
    boolean isLegal = false;
    String ParticipantName =null;
    Long SaccadeIndex =null;
    Long SaccadeNumber = null;
    Double RelativeSaccadeDirection = null;
    Double SaccadeLength = null;
    Double SaccadeLengthAverage = null;
    Double SaccadeLengthAll = null;
    Integer GazePointX_MCSpx = null;
    Integer GazePointY_MCSpx = null;
    String MediaName = null;
    Integer test = null;

    public Saccade(Record pri, Record current) {
        if(pri == null) {
            this.isLegal =true;
            this.ParticipantName = current.getParticipantName();
            this.SaccadeIndex = current.getSaccadeIndex();
            this.RelativeSaccadeDirection = -1d;
            this.SaccadeLength = -1d;
            this.GazePointX_MCSpx = current.getGazePointX_MCSpx();
            this.GazePointY_MCSpx = current.getGazePointY_MCSpx();
            this.MediaName = current.getMediaName();
        } else {
            this.ParticipantName = current.getParticipantName();
            this.SaccadeIndex = current.getSaccadeIndex();

            this.GazePointX_MCSpx = current.getGazePointX_MCSpx();
            this.GazePointY_MCSpx = current.getGazePointY_MCSpx();

            this.RelativeSaccadeDirection = getDirection(pri, current);
            this.SaccadeLength = getSaccadeLength(pri, current);
            this.MediaName = current.getMediaName();
        }

        if(pri == null || pri.getGazePointY_MCSpx() == -1 || pri.getGazePointX_MCSpx() == -1 || current.getGazePointX_MCSpx() == -1 || current.getGazePointY_MCSpx()==-1) {
            this.test = -1;
        } else {
            this.test = current.getGazePointX_MCSpx()-pri.getGazePointX_MCSpx();
        }
    }

    private Double getDirection(Record pri, Record current) {
        if(pri.getGazePointY_MCSpx() == -1 || pri.getGazePointX_MCSpx() == -1 || current.getGazePointX_MCSpx() == -1 || current.getGazePointY_MCSpx()==-1) {
            return Double.MIN_VALUE;
        }

        if((current.getGazePointX_MCSpx()-pri.getGazePointX_MCSpx()) == 0) {
            if((current.getGazePointY_MCSpx()-pri.getGazePointY_MCSpx())>0) {
                return 90d;
            } else if((current.getGazePointY_MCSpx()-pri.getGazePointY_MCSpx())<0) {
                return -90d;
            } else {
                return 0d;
            }
        }

        Double result = Math.toDegrees(Math.atan(Double.valueOf(String.valueOf(current.getGazePointY_MCSpx()-pri.getGazePointY_MCSpx()))/Double.valueOf(String.valueOf(current.getGazePointX_MCSpx()-pri.getGazePointX_MCSpx()))));

        return result;
    }

    private Double getSaccadeLength(Record pri, Record current) {
        if(pri.getGazePointY_MCSpx() == -1 || pri.getGazePointX_MCSpx() == -1 || current.getGazePointX_MCSpx() == -1 || current.getGazePointY_MCSpx()==-1) {
            return 0d;
        }

        Double result = Math.sqrt(Math.pow(Double.valueOf(String.valueOf(current.getGazePointY_MCSpx()-pri.getGazePointY_MCSpx())), 2) + Math.pow(Double.valueOf(String.valueOf(current.getGazePointX_MCSpx()-pri.getGazePointX_MCSpx())), 2));
        return result;
    }

    public boolean isLegal() {
        return isLegal;
    }

    public void setLegal(boolean legal) {
        isLegal = legal;
    }

    public String getParticipantName() {
        return ParticipantName;
    }

    public void setParticipantName(String participantName) {
        ParticipantName = participantName;
    }

    public Long getSaccadeIndex() {
        return SaccadeIndex;
    }

    public void setSaccadeIndex(Long saccadeIndex) {
        SaccadeIndex = saccadeIndex;
    }

    public Long getSaccadeNumber() {
        return SaccadeNumber;
    }

    public void setSaccadeNumber(Long saccadeNumber) {
        SaccadeNumber = saccadeNumber;
    }

    public Double getRelativeSaccadeDirection() {
        return RelativeSaccadeDirection;
    }

    public void setRelativeSaccadeDirection(Double relativeSaccadeDirection) {
        RelativeSaccadeDirection = relativeSaccadeDirection;
    }

    public Double getSaccadeLength() {
        return SaccadeLength;
    }

    public void setSaccadeLength(Double saccadeLength) {
        SaccadeLength = saccadeLength;
    }

    public Double getSaccadeLengthAverage() {
        return SaccadeLengthAverage;
    }

    public void setSaccadeLengthAverage(Double saccadeLengthAverage) {
        SaccadeLengthAverage = saccadeLengthAverage;
    }

    public Double getSaccadeLengthAll() {
        return SaccadeLengthAll;
    }

    public void setSaccadeLengthAll(Double saccadeLengthAll) {
        SaccadeLengthAll = saccadeLengthAll;
    }

    public Integer getGazePointX_MCSpx() {
        return GazePointX_MCSpx;
    }

    public void setGazePointX_MCSpx(Integer gazePointX_MCSpx) {
        GazePointX_MCSpx = gazePointX_MCSpx;
    }

    public Integer getGazePointY_MCSpx() {
        return GazePointY_MCSpx;
    }

    public void setGazePointY_MCSpx(Integer gazePointY_MCSpx) {
        GazePointY_MCSpx = gazePointY_MCSpx;
    }

    public String getMediaName() {
        return MediaName;
    }

    public void setMediaName(String mediaName) {
        MediaName = mediaName;
    }

    public Integer getTest() {
        return test;
    }

    public void setTest(Integer test) {
        this.test = test;
    }
}
