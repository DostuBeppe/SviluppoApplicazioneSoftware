package businesslogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SummarySheet  {
    private String title;
    private String note;
    private Map<Integer,Shift> shifts;
    private Map<Integer,ShiftTask> stList;
    private Map<Integer,Task> tasks;

    public SummarySheet(String title){
        this.title=title;
        /*shifts= new HashMap<>();
        stList= new HashMap<>();
        tasks= new HashMap<>();*/
    }
   /* public Task addTask(int quantity,int estimateTime,MenuItem item){
        Task task= new Task();
        tasks.

    }*/
   public void loadShift(String eventDate){
        shifts= CateringAppManager.dataManager.loadShifts(eventDate);
   }

}
