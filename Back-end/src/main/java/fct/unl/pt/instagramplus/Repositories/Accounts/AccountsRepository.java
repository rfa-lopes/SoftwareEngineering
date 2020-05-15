package fct.unl.pt.instagramplus.Repositories.Accounts;

import fct.unl.pt.instagramplus.Models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Long> {

    Account getAccountById(Long id);

    Account getAccountByEmail(String email);

    Account getAccountByUsername(String username);

    boolean existsAccountByEmailOrUsername(String email, String name);

}
