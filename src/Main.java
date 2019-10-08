import businesslogic.CateringAppManager;
import businesslogic.Recipe;
import businesslogic.User;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class Main extends Application {

    private Stage primaryStage;
    private CateringAppManager app;

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;
        this.app = CateringAppManager.getInstance();
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent main = mainLoader.load();
        Scene mainScene = new Scene(main);
        LocalDateTime time= LocalDateTime.of(2019,10,1,0,0);
        DateTimeFormatter format= DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println(time.format(format));
        MainController mainController = mainLoader.getController();

        primaryStage.setScene(mainScene);
        primaryStage.setWidth(1600);
        primaryStage.setHeight(800);
        // primaryStage.setMaximized(true);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Platform.exit();
            }
        });
        primaryStage.show();
    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}
