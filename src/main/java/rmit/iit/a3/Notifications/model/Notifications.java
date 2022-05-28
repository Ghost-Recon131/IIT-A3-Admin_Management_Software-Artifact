package rmit.iit.a3.Notifications.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rmit.iit.a3.util.NotificationType;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "notifications")
public class Notifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notificationID")
    private Long notificationID;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private NotificationType notificationType;

    @Column(name = "event_info_1")
    private String eventInfo1;

    @Column(name = "attack_time")
    private Date attackTime = new Date();

    // Automatically set the time on creating notification object
    @PrePersist
    protected void onCreate(){
        this.attackTime = new Date();
    }

    public Notifications(NotificationType notificationType, String eventInfo1) {
        this.notificationType = notificationType;
        this.eventInfo1 = eventInfo1;
    }

}
