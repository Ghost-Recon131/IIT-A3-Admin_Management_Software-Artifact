package rmit.iit.a3.Notifications.service;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import rmit.iit.a3.Notifications.model.Notifications;
import rmit.iit.a3.Notifications.repository.NotificationsRepository;
import rmit.iit.a3.util.NotificationType;

import java.util.List;

@Service
@AllArgsConstructor
public class NotificationsService {

    private NotificationsRepository notificationsRepository;
    private final Logger logger = LogManager.getLogger(this.getClass());

    // Adds dummy data if database is empty
    public void generateDummyData(){
        if(notificationsRepository.findAll().isEmpty()){
            Notifications malwareAttackNotification = new Notifications(NotificationType.MALWARE_ATTACK,"PA56A2NMHV");
            Notifications phishingAttackNotification = new Notifications(NotificationType.PHISHING,"9o21qjhr3fw@gmail.com");
            Notifications intrusionNotification = new Notifications(NotificationType.INTRUSION,"CIVUUHAO9M");
            Notifications zeroDayAttackNotification = new Notifications(NotificationType.ZERO_DAY,"Potential CVE in WebServer");

            notificationsRepository.save(malwareAttackNotification);
            notificationsRepository.save(phishingAttackNotification);
            notificationsRepository.save(intrusionNotification);
            notificationsRepository.save(zeroDayAttackNotification);
        }
    }


    // Remove single item
    public void deleteItem(Long notificationID){
        try{
            notificationsRepository.deleteByNotificationID(notificationID);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    // Remove all notifications in database
    public void clearAll(){
        if(!notificationsRepository.findAll().isEmpty()){
            List<Notifications> allNotifications = notificationsRepository.findAll();
            for (Notifications allNotification : allNotifications) {
                notificationsRepository.deleteByNotificationID(allNotification.getNotificationID());
            }
        }
    }


}
