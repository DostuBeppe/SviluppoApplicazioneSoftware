package businesslogic;

import java.util.*;

public class ShiftTask  {
    private Task task;
    private Shift shift;
    private int id;
    private Map<Integer,Shift> shifts;
    private Date date;
    private int summarySheetId;
    private String name;
    private int estimatedTime;
    private int quantity;
    private String numberShift;
    private String nameStaff;
    private boolean status;
    private String statusString;
    private Map<Integer,Staff> staffList;
    public ShiftTask(int ssId){
        this.summarySheetId= ssId;
        staffList= new HashMap<>();
        shifts= new HashMap<>();
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
        name="Preparare: "+task.getItem().toString();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {

        shifts.put(shift.getShiftId(),shift);
        setNumberShift(Integer.toString(shift.getNumber()));
    }
    public void setChoosenStaff(Staff staff){

        staffList.put(staff.getUserId(),staff);
        setNameStaff(staff.getName());
    }
    public void setAllChosenStaff(Map csm){
        this.staffList=csm;
    }
    public Map<Integer,Staff> getChoosenStaff(){
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

    @Override
    public String toString() {
        System.out.println("task: "+task.getItem().toString());
        return task.getItem().toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {

        this.status = status;
        task.setStatus(status);
    }

    public int getEstimatedTime() {
        return task.getEstimatedTime();
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
        task.setEstimatedTime(estimatedTime);
    }

    public int getQuantity() {
        return task.getQuantity();
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        task.setQuantity(quantity);
    }

    public String getNumberShift() {
        List<Shift> list= new ArrayList<>(shifts.values());
        this.numberShift="";
        for (Shift s:list
        ) {
            if(this.numberShift!=""){
                this.numberShift = this.numberShift+","+s.getNumber();
            }else{
                this.numberShift = Integer.toString(s.getNumber());
            }
        }
        return numberShift;
    }


    public String getNameStaff() {
        List<Staff> list= new ArrayList<>(staffList.values());
        this.nameStaff="";
        for (Staff s:list
             ) {
            if(this.nameStaff!=""){
                this.nameStaff = this.nameStaff+","+s.getName();
            }else{
                this.nameStaff = s.getName();
            }
        }
        return nameStaff;
    }

    public void setNameStaff(String nameStaff) {
        if(this.nameStaff!=null){
            this.nameStaff = this.nameStaff+","+nameStaff;
        }else{
            this.nameStaff = nameStaff;
        }

    }
    public void setNumberShift(String numberShift) {
        if(this.numberShift!=null){
            this.numberShift = this.numberShift+","+numberShift;
        }else{
            this.numberShift = numberShift;
        }

    }
    public void deleteAssign(){
        staffList.clear();
        shifts.clear();
        shift=null;
        this.numberShift="";
        this.nameStaff="";
    }

    public Map<Integer, Shift> getShifts() {
        return shifts;
    }

    public int getSummarySheetId() {
        return summarySheetId;
    }

    public void setSummarySheetId(int summarySheetId) {
        this.summarySheetId = summarySheetId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setShifts(Map shm){
        shifts=shm;
    }

    public String getStatusString() {
        return task.getStatusString();
    }

    public void setStatusString(String statusString) {
        this.statusString = statusString;
    }
}
