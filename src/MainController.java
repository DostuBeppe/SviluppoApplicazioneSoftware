import businesslogic.CateringAppManager;
import businesslogic.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Label errorLabel;
    private boolean first=true;
    private User u=null;
    @FXML
    public void initialize() {

    }
    @FXML
    private void handleButtonAction(ActionEvent event) {
        Button obj=(Button)event.getSource();
        if(obj.getId().equals(loginButton.getId())) {
            System.out.println("pressed");
            String userText = userName.getText();
            u = CateringAppManager.dataManager.loadUser(userText);
            if (u != null) {
                if (u.getRole().equals("c")) {
                    CateringAppManager.userManager.setCurrentUser(u);
                    if (first) {
                        try {
                            FXMLLoader eventListLoader = new FXMLLoader(getClass().getResource("eventlist.fxml"));
                            Parent eventList = eventListLoader.load();
                            EventListController eventListController = eventListLoader.getController();
                            eventListController.initialize(this);
                            mainPane.setCenter(eventList);
                        } catch (IOException exc) {
                            exc.printStackTrace();
                        }
                        first = false;
                        errorLabel.setText("");
                    }
                } else {
                    first = true;
                    errorLabel.setText("non sei un utente autorizzato");
                }
            } else{
                first = true;
                errorLabel.setText("non sei un utente del sistema");
            }
        }else if(obj.getId().equals(backButton.getId())){
            mainPane.setCenter(null);
            System.out.println("indietro");
        }

    }
    public BorderPane getMainPane(){
        return this.mainPane;
    }
}
