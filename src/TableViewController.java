import businesslogic.*;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class TableViewController {

    @FXML
    private TableView<ShiftTask> table;
    @FXML
    private TableColumn<Task, String> title;
    @FXML
    private TableColumn<Task, Integer>  time;
    @FXML
    private TableColumn<Task, Integer>  quantity;
    @FXML
    private TableColumn<Shift, Integer>  number;
    @FXML
    private TableColumn<Staff, String>  name;

    public void initialize(){
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->
                CateringAppManager.eventManager.getCurrentEvent().getCurrentSummarySheet().setCurrentShiftTask(newSelection));

    }

    /*@Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setCellValueFactory(
                new PropertyValueFactory<Email, String>("id"));
        object.setCellValueFactory(
                new PropertyValueFactory<Email, String>("argument"));
        sender.setCellValueFactory(
                new PropertyValueFactory<Email, String>("sender"));
        date.setCellValueFactory(
                new PropertyValueFactory<Email, String>("date"));
    }*/
}
