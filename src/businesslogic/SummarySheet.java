package businesslogic;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

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
    private ShiftTask currentShiftTask;
    private int taskId=0;
    private int stListId=0;
    private int shiftId=0;
    private ObservableList<ShiftTask> observableList;
    private ArrayList<ShiftTask> a;
    public SummarySheet(String title){
        this.title=title;
        shifts= new HashMap<>();
        stList= new HashMap<>();
        tasks= new HashMap<>();
        a= new ArrayList<>();
        observableList = FXCollections.observableList(a);


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

    public ObservableList<ShiftTask> getObservableList() {
        return observableList;
    }

    public Map<Integer, ShiftTask> getStList() {
        return stList;
    }
}
