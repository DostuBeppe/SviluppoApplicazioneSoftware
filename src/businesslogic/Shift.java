package businesslogic;

public class Shift {
    private String date;
    private String start;
    private String end;
    private int shiftId;
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
}
