import businesslogic.CateringAppManager;
import businesslogic.Shift;
import businesslogic.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StaffController {

    private List<Staff> staffList;
    private ObservableList<Staff> observableStaffList;
    // private MenuEditController menuEditController;
    private Staff selectedStaff;
    private  Shift currentShift;
    private EditPanelController main;
    @FXML
    private ListView<Staff> staffListView;
    @FXML
    private Label menuName;
    @FXML
    private BorderPane mainContainer;

    @FXML
    private BorderPane menuListPane;

    private BorderPane eventEditPane;
    @FXML
    private Button selectEventButton;

    @FXML
    public void initialize(EditPanelController main) {
        this.main=main;
        currentShift= CateringAppManager.billboardManager.getCurrentShift();
        staffListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.resetStaffList();


        staffListView.getSelectionModel().selectedIndexProperty().addListener((observable) -> {
            selectedStaff = staffListView.getSelectionModel().getSelectedItem();

        });
        System.out.println("loaded staff controller");
    }

    private void resetStaffList() {
       // menuName.setText(CateringAppManager.eventManager.getCurrentEvent().getMenu().getTitle());
        Map staffMap = currentShift.getStafList();
        staffList= new ArrayList<>(staffMap.values());
        observableStaffList = FXCollections.observableList(staffList);
        staffListView.setItems(observableStaffList);
    }

}
