package rmit.iit.a3.Logs.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "logs")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "logID")
    private Long logID;

    @Column(name = "user_id")
    private Long userID;

    // Increased length from default 255
    @Column(name = "data", length = 4096)
    private String data;

    @Column(name = "creat_time")
    private Date createTime = new Date();

    @Column(name = "file_size")
    private String fileSize;

    // Automatically set the time on creating log
    @PrePersist
    protected void onCreate(){
        this.createTime = new Date();
    }

    public Log(Long userID, String data, String fileSize) {
        this.userID = userID;
        this.data = data;
        this.fileSize = fileSize;
    }

}
