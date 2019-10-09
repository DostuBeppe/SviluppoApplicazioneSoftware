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
    private int id;
    private Map<Integer,Shift> shifts;
    private Map<Integer,ShiftTask> stList;
    private Map<Integer,Task> tasks;
    private Map<Integer,Staff> staffList;
    private ShiftTask currentShiftTask;
    private TableView table;
    private TableView table1;
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
       System.out.println("currentSt: "+this.currentShiftTask.getId());
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
       st.setId(stListId);
       stList.put(stListId,st);
       observableList.add(st);
        System.out.println("added shifttask:"+stListId+" item id: "+item.getItemId());
       stListId++;
       return st;
    }
    public void addShiftTask(ShiftTask st){
       System.out.println("added from db: "+st.getId());
        stList.put(st.getId(),st);
        observableList.add(st);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TableView getTable1() {
        return table1;
    }

    public void setTable1(TableView table1) {
        this.table1 = table1;
    }
    public void removeST(int stId){
       int oldSize=observableList.size();
       ShiftTask st= stList.get(stId);
       observableList.remove(st);
       stList.remove(stId);
       if(st==null){
           System.out.println("null ");
       }
       System.out.println("removed st: "+stId+" old: "+oldSize+" new: "+observableList.size());
    }
}
