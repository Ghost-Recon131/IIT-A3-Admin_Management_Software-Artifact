package rmit.iit.a3.JavaFx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import rmit.iit.a3.util.SwitchSceneUtil;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("MLModel.fxml")
public class MLModelController implements Initializable{

    private final Logger logger = LogManager.getLogger(this.getClass());
    private final FxWeaver fxWeaver;

    public MLModelController(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }

    @FXML
    private ImageView notificationIcon, homeIcon;
    @FXML
    private Label actionLabel;
    @FXML
    private TextArea textArea;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textArea.appendText("1. Lorem Ipsum");
        textArea.appendText("\n2. ultrices purus");
        textArea.appendText("\n3. diam vestibulum");
    }

    public void addSpecification(){
        actionLabel.setText("Added specification");
    }

    public void editSpecification(){
        // No planned function
        actionLabel.setText("");
    }

    public void removeSpecification(){
        // No planned function
        actionLabel.setText("");
    }

    public void backToDashboard(MouseEvent event){
        try{
            SwitchSceneUtil.switchFXMLMouseEvent(event, fxWeaver.loadView(DashboardController.class));
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    public void viewNotification(MouseEvent event){
        try{
            SwitchSceneUtil.switchFXMLMouseEvent(event, fxWeaver.loadView(NotificationController.class));
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

}
