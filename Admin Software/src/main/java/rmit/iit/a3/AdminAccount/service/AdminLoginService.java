package rmit.iit.a3.AdminAccount.service;

import javafx.scene.control.Label;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminLoginService {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private AuthenticationManager authenticationManager;

    // Attempt to log in
    public boolean login(String username, String password, Label errorLabel){
        boolean loginSuccess = false;

        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            loginSuccess = true;
            errorLabel.setText("");
        }catch(Exception e){
            errorLabel.setText("Invalid credentials!");
            logger.error("Failed to login" + e);
        }
        return loginSuccess;
    }

}
