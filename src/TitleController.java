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
    private EditPanelController main;
    @FXML
    public void initialize(EditPanelController main) {
        this.main=main;
    }
    @FXML
    private void handleButtonAction(ActionEvent event) {

        Button obj=(Button)event.getSource();
        if(obj.getId().equals(send.getId())){

            try {
                    FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
                    Parent menu = menuLoader.load();
                    MenuController menuController = menuLoader.getController();
                    menuController.initialize(main);
                    main.getControllPane().setLeft(menu);

                } catch (IOException exc) {
                    exc.printStackTrace();
                }
            try {
                FXMLLoader sheetLoader = new FXMLLoader(getClass().getResource("summary_sheet.fxml"));
                Parent sheet = sheetLoader.load();
                SummarySheetController sheetController = sheetLoader.getController();
                sheetController.initialize(main);
                main.getControllPane().setCenter(sheet);

            } catch (IOException exc) {
                exc.printStackTrace();
            }
        }

    }

}
