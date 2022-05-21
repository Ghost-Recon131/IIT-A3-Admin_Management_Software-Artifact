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
import rmit.iit.a3.AdminAccount.request.RegisterRequest;
import rmit.iit.a3.AdminAccount.service.AdminAccountService;
import rmit.iit.a3.util.FormatCheckerUtil;
import rmit.iit.a3.util.SwitchSceneUtil;


@Component
@FxmlView("Register.fxml")
public class RegisterController {

    @Autowired
    private AdminAccountService adminAccountService;
    @Autowired
    private FormatCheckerUtil formatCheckerUtil;

    private final Logger logger = LogManager.getLogger(this.getClass());

    private final FxWeaver fxWeaver;

    @FXML
    private Label errorLabel, emailError, passwordError, secretQuestionError, secretQuestionAnswerError;
    @FXML
    private TextField email, secretQuestion;
    @FXML
    private PasswordField password, confirmPassword, sqAnswer;

    public RegisterController(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }

    public void register(){
        RegisterRequest registerRequest = null;

        boolean infoValid = validateRegisterInfo();

        if(infoValid){
            try{
                registerRequest = new RegisterRequest(
                        email.getText(),
                        password.getText(),
                        secretQuestion.getText(),
                        sqAnswer.getText()
                );
            }catch (Exception e){
                logger.error(e.getMessage());
            }

            if(registerRequest != null){
                adminAccountService.createNewAdminAccount(registerRequest,errorLabel);
                errorLabel.setText("Account created successfully! Please return to login.");
            }
        }

    }

    // Checks that all fields are valid
    public boolean validateRegisterInfo(){
        boolean emailValid = formatCheckerUtil.checkTextFieldEntry(email);
        if(!emailValid && !email.getText().contains("@")){
            emailError.setText("Email cannot be empty! Or missing '@' character!");
        }else{
            emailError.setText("");
        }

        boolean passwordMatch = formatCheckerUtil.checkPasswordMatch(password, confirmPassword);
        if(!passwordMatch){
            passwordError.setText("Passwords do not match!");
        }else{
            passwordError.setText("");
        }

        boolean secretQuestionValid = formatCheckerUtil.checkTextFieldEntry(secretQuestion);
        if(!secretQuestionValid){
            secretQuestionError.setText("Secret question cannot be empty!");
        }else{
            secretQuestionError.setText("");
        }


        boolean sQAnswerValid = formatCheckerUtil.checkTextFieldEntry(sqAnswer);
        if(!sQAnswerValid){
            secretQuestionAnswerError.setText("Secret question answer cannot be empty!");
        }else{
            secretQuestionAnswerError.setText("");
        }

        return emailValid && passwordMatch && secretQuestionValid && sQAnswerValid;
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
