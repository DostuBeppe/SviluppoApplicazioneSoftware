import businesslogic.CateringAppManager;
import businesslogic.Event;
import businesslogic.MenuItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class MenuController {

    private List<MenuItem> items;
    private ObservableList<MenuItem> observableEvents;
    // private MenuEditController menuEditController;
    private MenuItem selectedItem;
    private MainController main;
    @FXML
    private ListView<MenuItem> menuItemList;

    @FXML
    private BorderPane mainContainer;

    @FXML
    private BorderPane menuListPane;

    private BorderPane eventEditPane;
    @FXML
    private Button selectEventButton;

    @FXML
    public void initialize(MainController main) {
        this.main=main;

        menuItemList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.resetItemList();


        menuItemList.getSelectionModel().selectedIndexProperty().addListener((observable) -> {
            selectedItem = menuItemList.getSelectionModel().getSelectedItem();

        });
    }

    private void resetItemList() {
        items = CateringAppManager.eventManager.getCurrentEvent().getMenu().getItemsWithoutSection();
        System.out.println(items.size());
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

        Button obj=(Button)event.getSource();
        if(obj.getId().equals(selectEventButton.getId())){

            try {
                FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
                Parent menu = menuLoader.load();
                EventListController menuController = menuLoader.getController();
                main.setMainPane(menu);
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        }

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
