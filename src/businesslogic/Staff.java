package businesslogic;

import java.util.Date;

public class Staff extends User {
    private boolean cook;
    private Busy busy;
    public Staff(){
        busy= new Busy();
    }

    public boolean isCook() {
        return cook;
    }

    public void setCook() {
        this.cook = true;
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

}
