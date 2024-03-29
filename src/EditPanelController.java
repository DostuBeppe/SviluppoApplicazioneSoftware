import businesslogic.CateringAppManager;
import businesslogic.SummarySheet;
import businesslogic.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;

public class EditPanelController{

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
        User u= CateringAppManager.userManager.getCurrentUser();
        boolean exist= CateringAppManager.dataManager.controllSSExist(u.getUserId());
        if(!exist){
            disableOpen(true);
            disableButton(true);
        }else{
            disableOpen(false);
            disableButton(false);
            disableCreate(true);
        }
    }
    @FXML
    private void handlButtonAction(ActionEvent event) {

        Button obj=(Button)event.getSource();
        if(obj.getId().equals(createSheet.getId())){
            disableOpen(true);
            disableCreate(true);
            disableShift(true);
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
        if(obj.getId().equals(eventBillBoard.getId())){
            System.out.println("Bottone tabellone evento cliccato");
            User u= CateringAppManager.userManager.getCurrentUser();
            SummarySheet ss=CateringAppManager.dataManager.loadChefSummarySheet(u.getUserId());
            CateringAppManager.eventManager.getCurrentEvent().setCurrentSummarySheet(ss);
            System.out.println("open sheet: "+ss.getTitle());
            Platform.runLater(()->{
                PoPup popup= new PoPup();
                popup.setConatainer(this);
                try {
                    disableButton(true);
                    popup.start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        if(obj.getId().equals(openSheet.getId())){

            User u= CateringAppManager.userManager.getCurrentUser();
            SummarySheet ss= CateringAppManager.dataManager.loadChefSummarySheet(u.getUserId());
            CateringAppManager.eventManager.getCurrentEvent().setCurrentSummarySheet(ss);

            System.out.println("open sheet: "+ss.getTitle());
            disableCreate(true);
            disableShift(false);
            disableButton(false);
            try {
                FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
                Parent menu = menuLoader.load();
                MenuController menuController = menuLoader.getController();
                menuController.initialize(this);
               controlPane.setLeft(menu);

            } catch (IOException exc) {
                exc.printStackTrace();
            }
            try {
                FXMLLoader sheetLoader = new FXMLLoader(getClass().getResource("summary_sheet.fxml"));
                Parent sheet = sheetLoader.load();
                TableViewController sheetController = sheetLoader.getController();
                sheetController.initialize(ss.getTitle());
                controlPane.setCenter(sheet);

            } catch (IOException exc) {
                exc.printStackTrace();
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
    public void disableButton(boolean action){
        eventBillBoard.setDisable(action);
    }
    public void disableOpen(boolean action){
        openSheet.setDisable(action);
    }
    public void disableCreate(boolean action){
        createSheet.setDisable(action);
    }
    public void disableShift(boolean action){
        shifts.setDisable(action);
    }
}
