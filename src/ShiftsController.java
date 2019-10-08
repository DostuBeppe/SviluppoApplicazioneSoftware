import businesslogic.CateringAppManager;
import businesslogic.MenuItem;
import businesslogic.Shift;
import businesslogic.Staff;
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
    private Button selectEventButton;

    @FXML
    public void initialize(EditPanelController main) {
        this.main=main;
        eventDate= CateringAppManager.eventManager.getCurrentEvent().getDateFormatted();
        shiftList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.resetShiftList();


        shiftList.getSelectionModel().selectedIndexProperty().addListener((observable) -> {
            selectedShift = shiftList.getSelectionModel().getSelectedItem();
            CateringAppManager.eventManager.getCurrentEvent().getCurrentSummarySheet().getCurrentShiftTask().setShift(selectedShift);
            System.out.println("selected shift");
            //CateringAppManager.eventManager.getCurrentEvent().getCurrentSummarySheet().addShift(selectedShift);
            CateringAppManager.billboardManager.setCurrentShift(selectedShift);
            CateringAppManager.eventManager.getCurrentEvent().getCurrentSummarySheet().getTable().refresh();
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

    private void resetShiftList() {
       // menuName.setText(CateringAppManager.eventManager.getCurrentEvent().getMenu().getTitle());
        Map shiftMap = CateringAppManager.billboardManager.showShifts(eventDate);
        shifts= new ArrayList<>(shiftMap.values());
        System.out.println("shifts: "+shifts.size());
        observableShifts = FXCollections.observableList(shifts);
        shiftList.setItems(observableShifts);
    }
   /* private void loadEventList() {
        eventList= new ListView<>();
        events = CateringAppManager.eventManager.getAllEvents();
        observableEvents = FXCollections.observableList(events);
        eventList.setItems(observableEvents);
    }*/
    @FXML
    private void handleButtonAction(ActionEvent event) {


    }
/*
    @FXML
    private void newMenuAction() {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Nuovo menu");
        dialog.setHeaderText("Creazione di un menu");
        dialog.setContentText("Inserisci opzionalmente un titolo:");
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(title -> {
            CateringAppManager.menuManager.createMenu(title);
            menuEditController.setup();
            mainContainer.setCenter(menuEditPane);
        });
    }

    @FXML
    private void editMenuAction() {
        CateringAppManager.menuManager.chooseMenu(this.selectedEvent);
        menuEditController.setup();
        mainContainer.setCenter(menuEditPane);
    }

    @FXML
    private void copyMenuAction() {
        CateringAppManager.menuManager.copyMenu(this.selectedEvent);
        menuEditController.setup();
        mainContainer.setCenter(menuEditPane);

    }

    @FXML
    private void deleteMenuAction() {
        CateringAppManager.menuManager.deleteMenu(this.selectedEvent);
        this.resetMenuList();
    }*/
}
