package businesslogic;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BillboardShifts {
    private Map<Integer,Shift> shifts;
    private String eventDate;
    public BillboardShifts(String eventDate){
        this.eventDate=eventDate;
        loadShifts(eventDate);
    }
    public void loadShifts(String eventDate){
        System.out.println("BS event date: >"+eventDate+"<");
        shifts= CateringAppManager.dataManager.loadShifts(eventDate);
    }
    public Map getShifts(){
        return shifts;
    }


}
