package businesslogic;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Task {

    private final IntegerProperty estimatedTime= new SimpleIntegerProperty();
    private final IntegerProperty quantity= new SimpleIntegerProperty();
    private final StringProperty title= new SimpleStringProperty();

    private int taskId;
    private MenuItem item;

    public Task() {
    }
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public final int getEstimatedTime() {
        return estimatedTime.get();
    }

    public final void setEstimatedTime(int estimatedTime) {
        this.estimatedTime.set(estimatedTime);
    }

    public final int getQuantity() {
        return quantity.get();
    }

    public final void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public MenuItem getItem() {
        return item;
    }

    public void setItem(MenuItem item) {
        this.item = item;
    }

    public StringProperty getTitle() {
        return title;
    }

    public final void setTitle(String title) {
        this.title.set(title);
    }

    public Task modifyTask(String title,int quantity,int estimatedTime,MenuItem item){
        setTitle(title);
        setEstimatedTime(estimatedTime);
        setQuantity(quantity);
        setItem(item);
        return this;
    }

    public IntegerProperty estimatedTimeProperty(){return estimatedTime;}
    public IntegerProperty quantityProperty(){return quantity;}
    public StringProperty titleProperty(){return title;}
}
