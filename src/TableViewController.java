import businesslogic.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.util.Map;

public class TableViewController {
    @FXML
    private Label sheetName;
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
    private ObservableList<ShiftTask> stList;
    private SummarySheet ss;

    public void initialize(String name){
        if(name!=null){
            sheetName.setText(name);
        }
        ss= new SummarySheet(name);
        CateringAppManager.eventManager.getCurrentEvent().setCurrentSummarySheet(ss);
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->
                CateringAppManager.eventManager.getCurrentEvent().getCurrentSummarySheet().setCurrentShiftTask(newSelection));

    }
    public void loadStList(){
        String date= CateringAppManager.eventManager.getCurrentEvent().getDateFormatted();
        Map shiftMap= CateringAppManager.billboardManager.showShifts(date);
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
