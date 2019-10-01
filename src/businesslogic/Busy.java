package businesslogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Busy  {
    private HashMap<String,Date> date;
    public Busy(){
        date= new HashMap<>();
    }
    public HashMap<String,Date> getDate(){
        return this.date;
    }

}
