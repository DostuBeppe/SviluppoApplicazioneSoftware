package businesslogic;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Map;

public class Shift {
    private final IntegerProperty number= new SimpleIntegerProperty();

    private String date;
    private String start;
    private String end;
    private int shiftId;
    private Map<Integer,Staff> stafList;

    public Shift(int number,int id,String date,String start,String end){
        this.shiftId= id;
        this.date=date;
        this.start=start;
        this.end=end;
        this.number.set(number);
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
    public final int getNumber() {
        return number.get();
    }
    public String toString(){
        return "numero: "+number.get()+" in data: "+date+" inizio: "+start+" fine: "+end;
    }

    public Map getStafList() {
        return stafList;
    }

    public IntegerProperty numberProperty(){return number;}
}
