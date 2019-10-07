import businesslogic.CateringAppManager;
import businesslogic.ShiftTask;
import businesslogic.SummarySheet;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class PoPup extends Application {

    private Stage stage;
    private CateringAppManager app;

    @FXML
    private TableView<ShiftTask> table;
    @FXML
    private TableColumn<ShiftTask, String> colShitTask;
    @FXML
    private TableColumn<ShiftTask, String>  colStaff;
    @FXML
    private TableColumn<ShiftTask, String>  colStatus;

    private ObservableList<ShiftTask> stList;

    public void initialize(String name){
        colShitTask.setCellValueFactory(new PropertyValueFactory<>("name"));
        colStaff.setCellValueFactory(new PropertyValueFactory<>("nameStaff"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }


    @Override
    public void start(Stage stage) throws Exception {

        System.out.println("Entro in popup");

        this.stage = stage;
        this.app = CateringAppManager.getInstance();
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("popup.fxml"));
        Parent main = mainLoader.load();
        Scene mainScene = new Scene(main);

        stage.setScene(mainScene);
        stage.show();
    }
}
