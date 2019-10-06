import businesslogic.CateringAppManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class EditPanelController {

    @FXML
    private BorderPane mainPane;
    @FXML
    private BorderPane controlPane;
    @FXML
    private BorderPane shiftPane;
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

    private boolean firstShift=true;
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
                controlPane.setCenter(title);
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        }
        if(obj.getId().equals(shifts.getId())){
            if(firstShift) {
                try {
                    FXMLLoader shiftsLoader = new FXMLLoader(getClass().getResource("shifts.fxml"));
                    Parent shift = shiftsLoader.load();
                    ShiftsController shiftController = shiftsLoader.getController();
                    shiftController.initialize(this);
                    shiftPane.setLeft(shift);
                } catch (IOException exc) {
                    exc.printStackTrace();
                }
                firstShift=false;
            }else {
                shiftPane.setLeft(null);
                shiftPane.setRight(null);
                firstShift=true;
            }
        }

    }
    public BorderPane getMainPane(){
        return this.mainPane;
    }
    public BorderPane getControllPane(){
        return this.controlPane;
    }
    public BorderPane getShiftPane(){
        return this.shiftPane;
    }
}
