package fct.unl.pt.instagramplus.Services.AccountsServices;

import fct.unl.pt.instagramplus.Models.Account;
import fct.unl.pt.instagramplus.Models.Follower;
import fct.unl.pt.instagramplus.Models.ProfileViewer;
import fct.unl.pt.instagramplus.Services.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountServiceInterface {

    Result<Long> createAccount(Account account);

    Result<Account> getAccount(Long id);

    Result<Void> deleteAccount(Long id);

    Result<Void> setNewViewer(Long thisAccount, Long seeThatAccount);

    Result<List<ProfileViewer>> getAccountViweres(Long id);

    Result<Void> followAccount(Follower follower);

    Result<List<Follower>> getAccountFollowings(Long id);

    Result<Void> changeVisibility(Long accountId);

    Result<Void> unfollowAccount(Follower follower);

    Result<List<Follower>> getFollowersAccount(Long id);

}
