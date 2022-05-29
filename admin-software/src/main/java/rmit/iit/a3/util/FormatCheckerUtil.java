package rmit.iit.a3.util;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FormatCheckerUtil {

    // Checks if field is empty
    public boolean checkTextFieldEntry(TextField textField){
        return !(textField.getText() == null) && !textField.getText().trim().isEmpty();
    }

    // Checks if provided password is empty
    public boolean checkPasswordFieldEntry(PasswordField passwordField){
        return !(passwordField.getText() == null) && !passwordField.getText().trim().isEmpty();
    }

    // Checks if passwords match
    public boolean checkPasswordMatch(PasswordField password, PasswordField confirmPassword){
        boolean isValid = false;
        boolean passwordValid = checkPasswordFieldEntry(password);
        boolean confirmPasswordValid = checkPasswordFieldEntry(confirmPassword);

        if(passwordValid && confirmPasswordValid && password.getText().equals(confirmPassword.getText())){
            isValid = true;
        }

        return isValid;
    }

}
