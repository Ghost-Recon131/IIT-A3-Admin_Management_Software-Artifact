package rmit.iit.a3.JavaFx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import rmit.iit.a3.util.SwitchSceneUtil;

@Component
@FxmlView("Dashboard.fxml")
public class DashboardController {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private final FxWeaver fxWeaver;

    public DashboardController(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }

    @FXML
    private ImageView notificationIcon;

    // Go to Bulk management page
    public void viewBulkManagement(ActionEvent event){
        try{
            SwitchSceneUtil.switchFXML(event, fxWeaver.loadView(BulkManagementController.class));
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    //TODO: Go to notification page
    public void viewNotification(ActionEvent event){
        try{
//            SwitchSceneUtil.switchFXML(event, fxWeaver.loadView(NotificationController.class));
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    //TODO: Go to logs page
    public void viewLogs(ActionEvent event){
        try{
//            SwitchSceneUtil.switchFXML(event, fxWeaver.loadView(LogsController.class));
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    //TODO: Go to mange ML Model page
    public void mangeMLModel(ActionEvent event){
        try{
//            SwitchSceneUtil.switchFXML(event, fxWeaver.loadView(MLModelController.class));
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    // Logout
    public void logout(ActionEvent event){
        try{
            SwitchSceneUtil.switchFXML(event, fxWeaver.loadView(LoginController.class));
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }


}
