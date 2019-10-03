import businesslogic.CateringAppManager;
import businesslogic.Event;
import businesslogic.Menu;
import businesslogic.MenuItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class MenuController {

    private List<MenuItem> items;
    private ObservableList<MenuItem> observableEvents;
    // private MenuEditController menuEditController;
    private MenuItem selectedItem;
    private EditPanelController main;
    @FXML
    private ListView<MenuItem> menuItemList;
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
        System.out.println("menu name: "+CateringAppManager.eventManager.getCurrentEvent().getMenu().getTitle());
        menuName.setText("Menu: "+CateringAppManager.eventManager.getCurrentEvent().getMenu().getTitle());
        menuItemList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.resetItemList();


        menuItemList.getSelectionModel().selectedIndexProperty().addListener((observable) -> {
            selectedItem = menuItemList.getSelectionModel().getSelectedItem();
            CateringAppManager.eventManager.getCurrentEvent().getCurrentSummarySheet().addShiftTask(selectedItem);

        });
        System.out.println("loaded menu controller");
    }

    private void resetItemList() {
       // menuName.setText(CateringAppManager.eventManager.getCurrentEvent().getMenu().getTitle());
        items = CateringAppManager.eventManager.getCurrentEvent().getMenu().getItemsWithoutSection();
        System.out.println("item: "+items.size());
        observableEvents = FXCollections.observableList(items);
        menuItemList.setItems(observableEvents);
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
