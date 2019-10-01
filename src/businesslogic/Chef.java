package businesslogic;

import java.util.Date;

public class Chef extends User {
    private Busy busy;
    public Chef(){
        busy= new Busy();
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
