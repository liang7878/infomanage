package model;

import org.apache.poi.ss.usermodel.Row;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Record {
    boolean isLegal = false;
    String ParticipantName = null;
    Long FixationIndex = null;
    Long SaccadeIndex = null;
    String GazeEventType = null;
    Integer GazePointX_MCSpx = null;
    Integer GazePointY_MCSpx = null;
    String MediaName = null;

    public Record(Row row) {
        if(!row.getCell(44).getStringCellValue().replaceAll("\"", "").equals("Saccade")) {
            this.isLegal = false;
        } else {
            this.GazeEventType = row.getCell(44).getStringCellValue().replaceAll("\"", "");
            this.ParticipantName = row.getCell(4).getStringCellValue().replaceAll("\"", "");
            this.SaccadeIndex = Long.valueOf(row.getCell(43).getStringCellValue().replaceAll("\"", ""));
            if(row.getCell(58).getStringCellValue().replaceAll("\"", "").equals("")) {
                this.GazePointX_MCSpx = -1;
            } else {
                this.GazePointX_MCSpx = Integer.valueOf(row.getCell(58).getStringCellValue().replaceAll("\"", ""));
            }

            if(row.getCell(59).getStringCellValue().replaceAll("\"", "").equals("")) {
                this.GazePointY_MCSpx = -1;
            } else {
                this.GazePointY_MCSpx = Integer.valueOf(row.getCell(59).getStringCellValue().replaceAll("\"", ""));
            }

            this.MediaName = row.getCell(11).getStringCellValue().replaceAll("\"", "");
            this.isLegal = true;
        }
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

    public Long getFixationIndex() {
        return FixationIndex;
    }

    public void setFixationIndex(Long fixationIndex) {
        FixationIndex = fixationIndex;
    }

    public Long getSaccadeIndex() {
        return SaccadeIndex;
    }

    public void setSaccadeIndex(Long saccadeIndex) {
        SaccadeIndex = saccadeIndex;
    }

    public String getGazeEventType() {
        return GazeEventType;
    }

    public void setGazeEventType(String gazeEventType) {
        GazeEventType = gazeEventType;
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
}










