import businesslogic.CateringAppManager;
import businesslogic.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class EventListController {

    private List<Event> events;
    private ObservableList<Event> observableEvents;
   // private MenuEditController menuEditController;
    private Event selectedEvent;

    @FXML
    private ListView<Event> eventList;

    @FXML
    private BorderPane mainContainer;

    @FXML
    private BorderPane menuListPane;

    private BorderPane eventEditPane;


    @FXML
    public void initialize() {
       /*  try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("eventlist.fxml"));
            eventEditPane = loader.load();
           // menuEditController = loader.getController();
            menuEditController.listen((publish -> {
                if (publish) {
                    CateringAppManager.menuManager.publish();
                }
                this.resetEventList();
                mainContainer.setCenter(menuListPane);
            }));

        } catch (IOException exc) {
            exc.printStackTrace();
        }*/

        eventList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.resetEventList();


      eventList.getSelectionModel().selectedIndexProperty().addListener((observable) -> {
            selectedEvent = eventList.getSelectionModel().getSelectedItem();
            System.out.println(selectedEvent.getMenuId());
        });
    }

    private void resetEventList() {
        events = CateringAppManager.eventManager.getAllEvents();
        System.out.println(events.size());
        observableEvents = FXCollections.observableList(events);
        eventList.setItems(observableEvents);
    }
    private void loadEventList() {
        eventList= new ListView<>();
        events = CateringAppManager.eventManager.getAllEvents();
        observableEvents = FXCollections.observableList(events);
        eventList.setItems(observableEvents);
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
