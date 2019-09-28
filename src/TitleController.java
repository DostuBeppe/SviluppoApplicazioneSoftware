import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class TitleController {

    @FXML
    private BorderPane mainPane;

    @FXML
    private TextField sheetName;
    @FXML
    private Button send;

    @FXML
    public void initialize(MainController main) {

    }
    @FXML
    private void handleButtonAction(ActionEvent event) {

        Button obj=(Button)event.getSource();
        if(obj.getId().equals(send.getId())){

        try {
                FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
                Parent menu = menuLoader.load();
                EventListController menuController = menuLoader.getController();


            } catch (IOException exc) {
                exc.printStackTrace();
            }
        }

    }

}
