package rmit.iit.a3.Notifications.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rmit.iit.a3.Notifications.model.Notifications;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface NotificationsRepository extends JpaRepository<Notifications, Long> {

    List<Notifications> findAll();

    Notifications getByNotificationID(Long notificationID);

    @Transactional
    void deleteByNotificationID(Long notificationID);
}
