package rmit.iit.a3.AdminAccount.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private String secretQuestion;
    private String sqAnswer;
}
