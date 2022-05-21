package rmit.iit.a3.AdminAccount.service;

import javafx.scene.control.Label;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import rmit.iit.a3.AdminAccount.model.AdminAccount;
import rmit.iit.a3.AdminAccount.repository.AdminAccountRepository;
import rmit.iit.a3.util.HashUtil;

@Service
@AllArgsConstructor
public class AdminLoginService {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private HashUtil hashUtil;
    private AdminAccountRepository adminAccountRepository;

    // Attempt to log in
    public boolean login(String username, String password, Label errorLabel){
        boolean loginSuccess = false;

        try{
            AdminAccount account = adminAccountRepository.getByUsername(username);
            if(hashUtil.getHash(password).equals(account.getPassword())){
                loginSuccess = true;
                errorLabel.setText("");
            }else{
                errorLabel.setText("Invalid credentials!");
            }
        }catch(Exception e){
            logger.error("Failed to login \n" + e);
        }
        return loginSuccess;
    }

}
