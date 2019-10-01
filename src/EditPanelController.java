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
    private Button shifts;
    @FXML
    private Button eventBillBoard;


    private MainController main;

    @FXML
    public void initialize(MainController main) {
        this.main=main;
        System.out.println("loaded edit panel controller");
        System.out.println(createSheet.getId());
    }
    @FXML
    private void handlButtonAction(ActionEvent event) {

        Button obj=(Button)event.getSource();
        System.out.println("load title");
        if(obj.getId().equals(createSheet.getId())){

            try {
                System.out.println("load title");
                FXMLLoader titleLoader = new FXMLLoader(getClass().getResource("title.fxml"));
                Parent title = titleLoader.load();
                TitleController titleController = titleLoader.getController();
                titleController.initialize(this);
                System.out.println("main: "+mainPane.getId());
                System.out.println("title: "+title.getId());
                mainPane.setCenter(title);
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        }

    }
    public BorderPane getMainPane(){
        return this.mainPane;
    }
}
