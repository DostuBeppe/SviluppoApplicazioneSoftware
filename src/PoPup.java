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
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class PoPup extends Application {

    private static PoPup singleInstance;
    private Stage stage;
    private CateringAppManager app;
    private Boolean alive=true;

    public static PoPup getInstance() {
        if (PoPup.singleInstance == null){
            PoPup.singleInstance = new PoPup();
        }
        return PoPup.singleInstance;
    }

    @Override
    public void start(Stage stage) throws Exception {

        if(alive) {
            System.out.println("Entro in popup");

            this.stage = stage;
            this.app = CateringAppManager.getInstance();
            FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("popup.fxml"));
            PopupController pc = mainLoader.getController();
            Parent main = mainLoader.load();
            Scene mainScene = new Scene(main);

            stage.setScene(mainScene);
            stage.show();
            alive=false;
        }
    }
}
