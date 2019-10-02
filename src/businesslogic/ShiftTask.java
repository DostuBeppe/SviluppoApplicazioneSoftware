package businesslogic;

import java.util.Date;
import java.util.HashMap;

public class ShiftTask  {
    private Task task;
    private Shift shift;
    private Date date;
    private int eventId;
    private HashMap<Integer,Staff> staffList;
    public ShiftTask(int eventId){
        this.eventId= eventId;
        staffList= new HashMap<>();
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }
    public void setChoosenStaff(Staff staff){
        staffList.put(staff.getUserId(),staff);
    }
    public HashMap<Integer,Staff> getChoosenStaff(){
        return staffList;
    }
    public void modifyAssign(Shift shift,Task task,Staff choosenStaff){
        if(task!=null){
            setTask(task);
        }
        if(choosenStaff!=null){
            setChoosenStaff(choosenStaff);
        }
        setShift(shift);
    }
}
