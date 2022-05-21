package rmit.iit.a3.AdminAccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rmit.iit.a3.AdminAccount.model.AdminAccount;

import java.util.List;

@Repository
public interface AdminAccountRepository extends JpaRepository<AdminAccount, Long> {

    List<AdminAccount> findAll();

    AdminAccount getByAccountID(Long id);

    AdminAccount getByUsername(String username);

    void deleteByAccountID(Long id);

}
