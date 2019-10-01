package businesslogic;

import java.util.Map;

public class Shift {
    private String date;
    private String start;
    private String end;
    private int shiftId;
    private Map<Integer,Staff> stafList;
    public Shift(int id,String date,String start,String end){
        this.shiftId= id;
        this.date=date;
        this.start=start;
        this.end=end;
    }

    public String getDate() {
        return date;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public int getShiftId() {
        return shiftId;
    }
    public void loadStaff(Map<Integer,Staff>staff){
        stafList= staff;
    }

}
