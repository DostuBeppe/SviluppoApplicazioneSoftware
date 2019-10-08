import businesslogic.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShiftsController {

    private List<Shift> shifts;
    private ObservableList<Shift> observableShifts;
    // private MenuEditController menuEditController;
    private Shift selectedShift;
    private  String eventDate;
    private EditPanelController main;
    @FXML
    private ListView<Shift> shiftList;
    @FXML
    private Label menuName;
    @FXML
    private BorderPane mainContainer;

    @FXML
    private BorderPane menuListPane;

    private BorderPane eventEditPane;
    @FXML
    private Button importButton;
    @FXML
    private Label errorLabel;

    @FXML
    public void initialize(EditPanelController main) {
        this.main=main;
        eventDate= CateringAppManager.eventManager.getCurrentEvent().getDateFormatted();
        shiftList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.resetShiftList();


        shiftList.getSelectionModel().selectedIndexProperty().addListener((observable) -> {
            selectedShift = shiftList.getSelectionModel().getSelectedItem();
            CateringAppManager.billboardManager.setCurrentShift(selectedShift);
            try {
                System.out.println("load staff");
                FXMLLoader staffLoader = new FXMLLoader(getClass().getResource("staffs.fxml"));
                Parent staff = staffLoader.load();
                StaffController staffController = staffLoader.getController();
                staffController.initialize(main);
                main.getShiftPane().setRight(staff);
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        });
        System.out.println("loaded shift controller");
    }
    @FXML
    private void handlButtonAction(ActionEvent event) {

        Button obj=(Button)event.getSource();
        if(obj.getId().equals(importButton.getId())){
            ShiftTask st=CateringAppManager.eventManager.getCurrentEvent().getCurrentSummarySheet().getCurrentShiftTask();
            if(st!=null){
                System.out.println("currentST shift: "+st.getId());
                Staff currentStaff=CateringAppManager.billboardManager.getCurrentStaff();
                st.setChoosenStaff(currentStaff);
                st.setShift(selectedShift);
                CateringAppManager.eventManager.getCurrentEvent().getCurrentSummarySheet().getTable().refresh();
                errorLabel.setText("");
            }else {
                errorLabel.setText("selezionare prima una mansione");
            }

        }

    }
    private void resetShiftList() {
       // menuName.setText(CateringAppManager.eventManager.getCurrentEvent().getMenu().getTitle());
        Map shiftMap = CateringAppManager.billboardManager.showShifts(eventDate);
        shifts= new ArrayList<>(shiftMap.values());
        System.out.println("shifts: "+shifts.size());
        observableShifts = FXCollections.observableList(shifts);
        shiftList.setItems(observableShifts);
    }

}
