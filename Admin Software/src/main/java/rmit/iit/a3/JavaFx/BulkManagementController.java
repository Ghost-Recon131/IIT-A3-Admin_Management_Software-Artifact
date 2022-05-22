package rmit.iit.a3.JavaFx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import rmit.iit.a3.util.SwitchSceneUtil;

@Component
@FxmlView("BulkManagement.fxml")
public class BulkManagementController {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private final FxWeaver fxWeaver;

    public BulkManagementController(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }

    @FXML
    private ImageView notificationIcon, homeIcon;
    @FXML
    private Label versionLabel, actionLabel;


    // No planned actual usage for these features, just displays some text
    public void deployToAllDevices(){
        actionLabel.setText("Deploying update to all devices...");
    }


    public void addDevices(){
        actionLabel.setText("Deploying software to new devices...");
    }


    public void removeDevice(){
        actionLabel.setText("Device removed from enterprise software management!");
    }


    public void updateSpecificDevice(){
        actionLabel.setText("Deploying software to device.");
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
//            SwitchSceneUtil.switchFXMLMouseEvent(event, fxWeaver.loadView(NotificationController.class));
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

}
