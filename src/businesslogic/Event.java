package businesslogic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event implements Cloneable {

    private User owner;
    private String title;
    private LocalDateTime date;
    private int chefId;
    private int eventId;
    private int menuId;
    private Menu menu;
    private SummarySheet currentSummarySheet;
    public Event(String title) {
        this.title= title;
    }
    public Event() {
    }

    public String getDateFormatted() {
        DateTimeFormatter format= DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(format);
    }
    public LocalDateTime getDate(){
        return date;
    }
    public void setDate(String date) {
       String [] splitted= date.split("/");
       int dd= Integer.parseInt(splitted[0]);
       int MM= Integer.parseInt(splitted[1]);
       int yyyy= Integer.parseInt(splitted[2]);
       this.date= LocalDateTime.of(yyyy,MM,dd,0,0);
    }

    public int getChefId() {
        return chefId;
    }

    public void setChefId(int chefId) {
        this.chefId = chefId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }


    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String toString() {
        return this.title ;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    public void setMenu(){
        this.menu= CateringAppManager.dataManager.loadMenuEvent(this.menuId);
    }
   public SummarySheet createSummarySheet(String title){
        SummarySheet ss= new SummarySheet(title);
        ss.loadShift(getDateFormatted());
        return ss;
   }

    public SummarySheet getCurrentSummarySheet() {
        return currentSummarySheet;
    }

    public void setCurrentSummarySheet(SummarySheet currentSummarySheet) {
        this.currentSummarySheet = currentSummarySheet;
    }
}
