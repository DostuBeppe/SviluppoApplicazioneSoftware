import businesslogic.CateringAppManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class EditPanelController {

    @FXML
    private BorderPane mainPane;

    @FXML
    private TextField userName;
    @FXML
    private Button createSheet;
    @FXML
    private Button editSheet;
    @FXML
    private Button openSheet;

    @FXML
    public void initialize(MainController main) {
        System.out.println("loaded edit panel controller");
    }
    @FXML
    private void handleButtonAction(ActionEvent event) {

        Button obj=(Button)event.getSource();
        System.out.println("load title");
        if(obj.getId().equals(createSheet.getId())){

            try {
                System.out.println("load title");
                FXMLLoader titleLoader = new FXMLLoader(getClass().getResource("title.fxml"));
                Parent title = titleLoader.load();
                TitleController titleController = titleLoader.getController();
                mainPane.setRight(title);
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        }

    }

}
