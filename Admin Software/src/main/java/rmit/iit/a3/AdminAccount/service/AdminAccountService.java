package rmit.iit.a3.AdminAccount.service;

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
import rmit.iit.a3.util.UserRole;

import java.awt.*;

@Service
@AllArgsConstructor
public class AdminAccountService implements UserDetailsService {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private AdminAccountRepository adminAccountRepository;


    // Save new admin to database
    public void createNewAdminAccount(RegisterRequest registerRequest, Label errorLabel){
        try{
            AdminAccount adminAccount = new AdminAccount(
                    registerRequest.getUsername(),
                    registerRequest.getPassword(),
                    registerRequest.getSecretQuestion(),
                    registerRequest.getSqAnswer(),
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
