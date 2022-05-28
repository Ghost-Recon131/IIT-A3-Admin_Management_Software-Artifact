package rmit.iit.a3.JavaFx;

import javafx.event.ActionEvent;
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
import rmit.iit.a3.AdminAccount.service.AdminAccountService;
import rmit.iit.a3.util.FormatCheckerUtil;
import rmit.iit.a3.util.SwitchSceneUtil;

@Component
@FxmlView("ForgotPassword.fxml")
public class ForgotPasswordController {

    @Autowired
    private FormatCheckerUtil formatCheckerUtil;
    @Autowired
    private AdminAccountService adminAccountService;
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final FxWeaver fxWeaver;

    public ForgotPasswordController(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }

    @FXML
    private Label errorLabel, passwordError;
    @FXML
    private TextField email, secretQuestion;
    @FXML
    private PasswordField password, confirmPassword, sqAnswer;


    // Attempt to change password
    public void resetPassword(){
        if(checkPasswordMatch()){
            try{
                adminAccountService.resetAccountPassword(email.getText(), confirmPassword.getText(), secretQuestion.getText(), sqAnswer.getText(), errorLabel);
            }catch (Exception e){
                logger.error(e.getMessage());
            }
        }
    }

    // check the entered password and confirm password matches
    public boolean checkPasswordMatch(){
        boolean passwordMatch = formatCheckerUtil.checkPasswordMatch(password, confirmPassword);
        if(!passwordMatch){
            passwordError.setText("Passwords do not match!");
        }else{
            passwordError.setText("");
        }
        return passwordMatch;
    }

    // Back to login page
    public void back(ActionEvent event){
        try{
            SwitchSceneUtil.switchFXML(event, fxWeaver.loadView(LoginController.class));
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

}
