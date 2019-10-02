import businesslogic.CateringAppManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController {

    @FXML
    private BorderPane mainPane;

    @FXML
    private TextField userName;
    @FXML
    private Button loginButton;
    @FXML
    private Button backButton;
    @FXML
    private Button forwardButton;

    @FXML
    public void initialize() {

    }
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("pressed");
        Button obj=(Button)event.getSource();
        if(obj.getId().equals(loginButton.getId())){
            String userText=userName.getText();
            CateringAppManager.userManager.setCurrentUser(CateringAppManager.dataManager.loadUser(userText));

            try {
                FXMLLoader eventListLoader = new FXMLLoader(getClass().getResource("eventlist.fxml"));
                Parent eventList = eventListLoader.load();
                EventListController eventListController = eventListLoader.getController();
                eventListController.initialize(this);
                mainPane.setCenter(eventList);
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        }else if(obj.getId().equals(backButton.getId())){
            System.out.println("indietro");
        }else if(obj.getId().equals(forwardButton.getId())){
            System.out.println("avanti");
        }

    }
    public BorderPane getMainPane(){
        return this.mainPane;
    }
}
