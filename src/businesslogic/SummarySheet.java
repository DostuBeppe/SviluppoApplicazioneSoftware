package businesslogic;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SummarySheet  {
    private String title;
    private String note;
    private Map<Integer,Shift> shifts;
    private Map<Integer,ShiftTask> stList;
    private Map<Integer,Task> tasks;
    private Map<Integer,Staff> staffList;
    private ShiftTask currentShiftTask;
    private TableView table;
    private Task currentTask;
    private int taskId=0;
    private int stListId=0;
    private int shiftId=0;
    private int chefId;
    private ObservableList<ShiftTask> observableList;
    public SummarySheet(String title){
        this.title=title;
        shifts= new HashMap<>();
        stList= new HashMap<>();
        tasks= new HashMap<>();
        staffList= new HashMap<>();
        observableList = FXCollections.observableArrayList();

    }
   /* public Task addTask(int quantity,int estimateTime,MenuItem item){
        Task task= new Task();
        tasks.

    }*/
   public void loadShift(String eventDate){
        shifts= CateringAppManager.dataManager.loadShifts(eventDate);
   }

    public ShiftTask getCurrentShiftTask() {
        return currentShiftTask;
    }

    public void setCurrentShiftTask(ShiftTask currentShiftTask) {
        this.currentShiftTask = currentShiftTask;
    }
    public Task addTask(MenuItem item){
       Task t= new Task();
       t.setItem(item);
       t.setTaskId(taskId);
       tasks.put(taskId,t);
       taskId++;
       return  t;
    }
    public ShiftTask addShiftTask(MenuItem item){
       ShiftTask st= new ShiftTask(CateringAppManager.eventManager.getCurrentEvent().getEventId());
       st.setTask(addTask(item));
       stList.put(stListId,st);
       observableList.add(st);
        System.out.println("added shifttask:"+stListId);
       stListId++;
       return st;
    }

    public void addStaff(Staff staff){
       staffList.put(staff.getStaffId(),staff);
    }
    public List getStaff(){
       return new ArrayList<Staff>(staffList.values());
    }
    public ObservableList<ShiftTask> getObservableList() {
        return observableList;
    }

    public Map<Integer, ShiftTask> getStList() {
        return stList;
    }

    public Task getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(Task currentTask) {
        this.currentTask = currentTask;
    }

    public TableView getTable() {
        return table;
    }

    public void setTable(TableView table) {
        this.table = table;
    }

    public int getChefId() {
        return chefId;
    }

    public void setChefId(int chefId) {
        this.chefId = chefId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Map<Integer, Shift> getShifts() {
        return shifts;
    }

    public void addShift(Shift shift) {
        shifts.put(shift.getShiftId(),shift);
    }

}
