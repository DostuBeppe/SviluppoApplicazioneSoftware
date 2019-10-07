import businesslogic.CateringAppManager;
import businesslogic.ShiftTask;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class PopupController {

    @FXML
    private TableView<ShiftTask> table;
    @FXML
    private BorderPane mainPain;
    @FXML
    private TableColumn<ShiftTask, String> colShiftTask;
    @FXML
    private TableColumn<ShiftTask, String>  colStaff;
    @FXML
    private TableColumn<ShiftTask, String>  colStatus;

    private ObservableList<ShiftTask> stList;

    public void initialize(){
        System.out.println("ARRIVO QUI //////////////////////////////////////");
        colShiftTask.setCellValueFactory(new PropertyValueFactory<>("name"));
        colStaff.setCellValueFactory(new PropertyValueFactory<>("nameStaff"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        loadStList();
        table.setItems(stList);
    }

    public void loadStList(){
        stList= CateringAppManager.eventManager.getCurrentEvent().getCurrentSummarySheet().getObservableList();

    }
}
