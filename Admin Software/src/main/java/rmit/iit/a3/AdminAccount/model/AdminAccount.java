package rmit.iit.a3.AdminAccount.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import rmit.iit.a3.util.UserRole;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "CourseInfo")
public class AdminAccount implements UserDetails {

    // Unique ID for account
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accountID")
    private Long accountID;

    @Column(unique = true, name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "secret_question")
    private String secretQuestion;

    @Column(name = "secret_question_answer")
    private String sqAnswer;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole userRole;

    // Default to enabled for new accounts, realistically should be reviewed
    @Column(name = "enabled")
    private Boolean enabled = true;

    @Column(name = "locked")
    private Boolean locked;

    // Constructor to create new accounts
    public AdminAccount(String username, String password, String secretQuestion, String sqAnswer, UserRole userRole) {
        this.username = username;
        this.password = password;
        this.secretQuestion = secretQuestion;
        this.sqAnswer = sqAnswer;
        this.userRole = userRole;
    }

    // UserDetails methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
