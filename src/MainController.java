import businesslogic.CateringAppManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController {

    @FXML
    private BorderPane mainPane;

    @FXML
    private TextField userName;

    @FXML
    public void initialize() {
        //userName.setText(CateringAppManager.userManager.getCurrentUser().toString());

        try {
            FXMLLoader eventListLoader = new FXMLLoader(getClass().getResource("eventlist.fxml"));
            Parent eventList = eventListLoader.load();
            EventListController eventListController = eventListLoader.getController();

            mainPane.setCenter(eventList);
        } catch (IOException exc) {
            exc.printStackTrace();
        }

    }
}
