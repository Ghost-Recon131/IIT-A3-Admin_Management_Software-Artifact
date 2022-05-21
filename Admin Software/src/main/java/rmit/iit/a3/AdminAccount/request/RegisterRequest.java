package rmit.iit.a3.AdminAccount.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String username;
    private String password;
    private String secretQuestion;
    private String sqAnswer;
}
