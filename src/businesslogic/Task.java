package businesslogic;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Task {

    private int taskId;
    private int estimatedTime;
    private int quantity;
    private String title;
    private MenuItem item;

    public Task() {
    }
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public  int getEstimatedTime() {
        return estimatedTime;
    }

    public  void setEstimatedTime(int estimatedTime) {
        this.estimatedTime=estimatedTime;
    }

    public  int getQuantity() {
        return quantity;
    }

    public  void setQuantity(int quantity) {
        this.quantity=quantity;
    }

    public MenuItem getItem() {
        return item;
    }

    public void setItem(MenuItem item) {
        this.item = item;
    }

    public String getTitle() {
        return title;
    }

    public  void setTitle(String title) {
        this.title=title;
    }

    public Task modifyTask(String title,int quantity,int estimatedTime,MenuItem item){
        setTitle(title);
        setEstimatedTime(estimatedTime);
        setQuantity(quantity);
        setItem(item);
        return this;
    }
}
