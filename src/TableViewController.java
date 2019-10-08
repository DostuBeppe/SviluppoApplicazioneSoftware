import businesslogic.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TableViewController {
    @FXML
    private Label sheetName;
    @FXML
    private BorderPane mainContainer;
    @FXML
    private TableView<ShiftTask> table;
    @FXML
    private TableColumn<ShiftTask, String> title;
    @FXML
    private TableColumn<ShiftTask, String>  time;
    @FXML
    private TableColumn<ShiftTask, String>  quantity;
    @FXML
    private TableColumn<ShiftTask, String>  number;
    @FXML
    private TableColumn<ShiftTask, String>  nameStaff;
    @FXML
    private Button saveButton;
    private ObservableList<ShiftTask> stList;
    private SummarySheet ss;
    private List<ShiftTask> stArray;
    public void initialize(String name){
        title.setCellValueFactory(new PropertyValueFactory<>("name"));
        time.setCellValueFactory(new PropertyValueFactory<>("estimatedTime"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        number.setCellValueFactory(new PropertyValueFactory<>("numberShift"));
        nameStaff.setCellValueFactory(new PropertyValueFactory<>("nameStaff"));
        SummarySheet css=CateringAppManager.eventManager.getCurrentEvent().getCurrentSummarySheet();
        if(css==null) {
            ss = new SummarySheet(name);
            CateringAppManager.eventManager.getCurrentEvent().setCurrentSummarySheet(ss);

            ss.setChefId(CateringAppManager.userManager.getCurrentUser().getUserId());
            if (name != null) {
                sheetName.setText(name);
                ss.setTitle(name);
            }
        }
        CateringAppManager.eventManager.getCurrentEvent().getCurrentSummarySheet().setTable(table);
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->
        {
            CateringAppManager.eventManager.getCurrentEvent().getCurrentSummarySheet().setCurrentShiftTask(newSelection);
           // CateringAppManager.eventManager.getCurrentEvent().getCurrentSummarySheet().setCurrentTask(newSelection.getTask());
            try {
              //  System.out.println("load staff");
                FXMLLoader editLoader = new FXMLLoader(getClass().getResource("edittask.fxml"));
                Parent edit = editLoader.load();
                EditTaskController editTaskController = editLoader.getController();
                editTaskController.initialize(this);
                mainContainer.setBottom(edit);
            } catch (IOException exc) {
                exc.printStackTrace();
            }

        });

        loadStList();
        table.setItems(stList);
       /* stList.addListener(new ListChangeListener() {

            @Override
            public void onChanged(ListChangeListener.Change change) {
                System.out.println("Detected a change! ");
                table.setItems(stList);
            }
        });*/


    }
    @FXML
    private void handleButtonAction(ActionEvent event) {

        Button obj=(Button)event.getSource();
        if(obj.getId().equals(saveButton.getId())){
            System.out.println("save sheet");
            CateringAppManager.dataManager.uploadSummarySheet();
        }

    }
    public void loadStList(){
        stList= CateringAppManager.eventManager.getCurrentEvent().getCurrentSummarySheet().getObservableList();

    }
    public TableView getTable(){
        return  table;
    }
}
