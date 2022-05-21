package rmit.iit.a3.JavaFx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rmit.iit.a3.AdminAccount.service.AdminLoginService;

@Component
@FxmlView("Login.fxml")
public class LoginController {

    @Autowired
    private AdminLoginService adminLoginService;

    private final Logger logger = LogManager.getLogger(this.getClass());

    private final FxWeaver fxWeaver;

    @FXML
    private Label errorLabel;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;

    public LoginController(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }

    public void signIn(){
        if(adminLoginService.login(email.getText(), password.getText(), errorLabel)){
            //TODO: Take user to main page
        }
    }

    public void forgotPassword(){

    }

    public void register(){

    }

}
