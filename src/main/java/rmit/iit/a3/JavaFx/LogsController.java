package rmit.iit.a3.JavaFx;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rmit.iit.a3.Logs.model.Log;
import rmit.iit.a3.Logs.repository.LogRepository;
import rmit.iit.a3.Logs.service.LogService;
import rmit.iit.a3.util.SwitchSceneUtil;

import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
@FxmlView("Logs.fxml")
public class LogsController implements Initializable {

    @Autowired
    private LogRepository logRepository;
    @Autowired
    private LogService logService;
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final FxWeaver fxWeaver;

    @FXML
    private Label downloadLabel;
    @FXML
    private ListView<String> listView;

    public LogsController(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logService.generateMockData();

        List<Log> allLogs = logRepository.findAll();

        for (Log log: allLogs) {
            String formattedString = log.getCreateTime().toString()
                    + "                      "
                    + log.getUserID().toString()
                    + "                      "
                    + log.getFileSize();
            listView.getItems().add(formattedString);
        }
    }

    public void downloadLogs(){
        try{
            Writer output = new FileWriter("FileOutput.csv");
            logService.generateCSV(output);
            downloadLabel.setText("File ready for export!");
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    public void backToDashboard(MouseEvent event){
        try{
            SwitchSceneUtil.switchFXMLMouseEvent(event, fxWeaver.loadView(DashboardController.class));
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    public void viewNotificationPage(MouseEvent mEvent){
        try{
            SwitchSceneUtil.switchFXMLMouseEvent(mEvent, fxWeaver.loadView(NotificationController.class));
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

}
