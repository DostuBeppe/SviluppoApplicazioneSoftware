import businesslogic.CateringAppManager;
import businesslogic.ShiftTask;
import businesslogic.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class EditTaskController {

    @FXML
    private TextField textTitle;

    @FXML
    private TextField textTime;

    @FXML
    private TextField textQuantity;

    @FXML
    private Button buttonEditTask;

    private TableViewController tabel;
    @FXML
    public void initialize(TableViewController main) {
        tabel=main;
    }

    @FXML
    private void handlButtonAction(ActionEvent event) {

        Button obj=(Button)event.getSource();
        System.out.println("load title");
        if(obj.getId().equals(buttonEditTask.getId())){
          // Task t= CateringAppManager.eventManager.getCurrentEvent().getCurrentSummarySheet().getCurrentTask();
           ShiftTask st= CateringAppManager.eventManager.getCurrentEvent().getCurrentSummarySheet().getCurrentShiftTask();
           System.out.println("name: "+st.getName());
           if(!textTitle.getText().equals("")){
               st.setName(textTitle.getText());
           }
            if(!textQuantity.getText().equals("")){
                st.setQuantity(Integer.parseInt(textQuantity.getText()));
            }
            if(!textTime.getText().equals("")){
                st.setEstimatedTime(Integer.parseInt(textTime.getText()));
            }


           tabel.getTable().refresh();
        }

    }


}
