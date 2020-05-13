package fct.unl.pt.instagramplus.Services.AccountsServices;

import fct.unl.pt.instagramplus.Models.Accounts.Account;
import fct.unl.pt.instagramplus.Models.ProfileViewer;
import fct.unl.pt.instagramplus.Services.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountServiceInterface {

    Result<Long> createAccount(Account account);

    Result<Account> getAccount(Long id);

    Result<Void> deteleAccount(Long id);

    Result<List<ProfileViewer>> gettAccountViweres(Long id);


}
