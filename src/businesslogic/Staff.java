package businesslogic;

import java.util.*;

public class Staff extends User {
    private boolean cook;
    private int staffId;
    private Busy busy;
    private List<Integer> selectedShifts;
    public Staff(){
        busy= new Busy();
        selectedShifts= new ArrayList<>();
    }

    public boolean isCook() {
        return cook;
    }

    public void setCook(int cook) {
        this.cook = (cook==1)?true:false;
    }
    public void freeBusy(Date date){
        busy.getDate().remove(date.toString());
    }
    public void setBusy(Date date){
        busy.getDate().put(date.toString(),date);
    }
    public boolean isBusy(Date date){
        Date bDate= busy.getDate().get(date.toString());
        if(date==null){
            return false;
        }else{
            return true;
        }
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }
    public void addSelectedShifts(int shiftId){
        selectedShifts.add(shiftId);
    }
    public List<Integer> getSelectedShifts(){
        return selectedShifts;
    }
}
