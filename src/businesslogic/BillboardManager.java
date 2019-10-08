package businesslogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BillboardManager {
    private BillboardShifts bilboardShift;
    private Shift currentShift;
    private Staff currentStaff;
    public BillboardManager(){}
    public void initialize(){}
    public Map showShifts(String eventDate){
        BillboardShifts bill= new BillboardShifts(eventDate);
        return bill.getShifts();
    }

    public Shift getCurrentShift() {
        return currentShift;
    }

    public void setCurrentShift(Shift currentShift) {
        this.currentShift = currentShift;
    }

    public Staff getCurrentStaff() {
        return currentStaff;
    }

    public void setCurrentStaff(Staff currentStaff) {
        this.currentStaff = currentStaff;
    }
}
