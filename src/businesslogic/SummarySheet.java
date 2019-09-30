package businesslogic;

import java.util.HashMap;
import java.util.List;

public class SummarySheet  {
    private String title;
    private String note;
    private HashMap<Integer,Shift> shifts;
    private HashMap<Integer,ShiftTask> stList;
    private HashMap<Integer,Task> tasks;

    public SummarySheet(String title){
        this.title=title;
        shifts= new HashMap<>();
        stList= new HashMap<>();
        tasks= new HashMap<>();
    }
    public Task addTask(int quantity,int estimateTime,MenuItem item){
        Task task= new Task()

    }
}
