package rmit.iit.a3.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SwitchSceneUtil {

    // Used to switch between different FXML scenes
    public static void switchFXML(ActionEvent event, Parent root) {
        Node node = (Node)event.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        stage.close();
        Scene scene = new Scene(root);
        Stage secondStage = new Stage();
        secondStage.setScene( scene );
        secondStage.show();
    }

}
