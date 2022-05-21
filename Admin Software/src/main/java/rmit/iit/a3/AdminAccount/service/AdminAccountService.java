package rmit.iit.a3.AdminAccount.service;

import javafx.scene.control.Label;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rmit.iit.a3.AdminAccount.model.AdminAccount;
import rmit.iit.a3.AdminAccount.repository.AdminAccountRepository;
import rmit.iit.a3.AdminAccount.request.RegisterRequest;
import rmit.iit.a3.util.HashUtil;
import rmit.iit.a3.util.UserRole;

@Service
@AllArgsConstructor
public class AdminAccountService implements UserDetailsService {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private AdminAccountRepository adminAccountRepository;
    private HashUtil hashUtil;


    // Save new admin to database
    public void createNewAdminAccount(RegisterRequest registerRequest, Label errorLabel){
        try{
            String hashedPassword = hashUtil.getHash(registerRequest.getPassword());
            String hashedSQAnswer = hashUtil.getHash(registerRequest.getSqAnswer());
            AdminAccount adminAccount = new AdminAccount(
                    registerRequest.getUsername(),
                    hashedPassword,
                    registerRequest.getSecretQuestion(),
                    hashedSQAnswer,
                    UserRole.ADMIN
            );
            adminAccountRepository.save(adminAccount);
        }catch (Exception e){
            logger.error(e.getMessage());
            errorLabel.setText("Failed to create account!");
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return adminAccountRepository.getByUsername(username);
    }

}
