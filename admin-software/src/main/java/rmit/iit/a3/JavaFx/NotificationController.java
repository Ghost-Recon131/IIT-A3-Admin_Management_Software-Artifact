package rmit.iit.a3.JavaFx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rmit.iit.a3.Notifications.model.Notifications;
import rmit.iit.a3.Notifications.repository.NotificationsRepository;
import rmit.iit.a3.Notifications.service.NotificationsService;
import rmit.iit.a3.util.SwitchSceneUtil;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static rmit.iit.a3.util.NotificationType.*;

@Component
@FxmlView("Notification.fxml")
public class NotificationController implements Initializable {

    @Autowired
    private NotificationsRepository notificationsRepository;
    @Autowired
    private NotificationsService notificationsService;
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final FxWeaver fxWeaver;

    public NotificationController(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }

    @FXML
    private ImageView notificationIcon, homeIcon;
    @FXML
    private Label actionLabel, detail1, detail2, detail3, fullInfo1, fullInfo2, fullInfo3;
    @FXML
    private ListView<Long> listView;
    List<Notifications> allNotificationObjects = new ArrayList<>();

    Notifications currentlyViewing;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        notificationsService.generateDummyData();

        try{
            // Get all notifications and adds it to list view
            allNotificationObjects = notificationsRepository.findAll();

            for (Notifications notifications : allNotificationObjects) {
                listView.getItems().add(notifications.getNotificationID());
            }

            // Sets the currently selected notifications
            listView.getSelectionModel().selectedItemProperty().addListener((arg0, arg1, arg2) -> {
                currentlyViewing = notificationsRepository.getByNotificationID(listView.getSelectionModel().getSelectedItem());

                // Show all info on a notification
                if(currentlyViewing.getNotificationType().equals(PHISHING)){
                    detail1.setText("Sender: ");
                    detail2.setText("Summary: ");
                    detail3.setText("Received time: ");
                }else if(currentlyViewing.getNotificationType().equals(ZERO_DAY)){
                    detail1.setText("Vulnerability: ");
                    detail2.setText("Attack Type: ");
                    detail3.setText("Time: ");
                }else{
                    detail1.setText("On Device: ");
                    detail2.setText("Attack Type: ");
                    detail3.setText("Time: ");
                }
                fullInfo1.setText(currentlyViewing.getEventInfo1());
                fullInfo2.setText(currentlyViewing.getNotificationType().toString());
                fullInfo3.setText(currentlyViewing.getAttackTime().toString());

            });
        }catch (Exception e) {
            logger.error(e.getMessage());
        }
    }


    public void markAsFalsePositive(ActionEvent event){
        actionLabel.setText("Marked as false positive! Deleting now.");

        // Delete entry then reload page
        notificationsService.deleteItem(currentlyViewing.getNotificationID());

        // Reload current page
        SwitchSceneUtil.switchFXML(event, fxWeaver.loadView(NotificationController.class));
    }


    public void backToDashboard(MouseEvent event){
        try{
            SwitchSceneUtil.switchFXMLMouseEvent(event, fxWeaver.loadView(DashboardController.class));
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

}
