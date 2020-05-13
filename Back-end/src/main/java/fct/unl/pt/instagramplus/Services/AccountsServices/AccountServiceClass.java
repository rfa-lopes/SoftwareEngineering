package fct.unl.pt.instagramplus.Services.AccountsServices;

import fct.unl.pt.instagramplus.Models.Accounts.Account;
import fct.unl.pt.instagramplus.Models.Follower;
import fct.unl.pt.instagramplus.Models.ProfileViewer;
import fct.unl.pt.instagramplus.Repositories.Accounts.AccountsRepository;
import fct.unl.pt.instagramplus.Repositories.Accounts.FollowersRepository;
import fct.unl.pt.instagramplus.Repositories.Accounts.ProfileViewersRepository;
import fct.unl.pt.instagramplus.Services.Result;
import fct.unl.pt.instagramplus.Utils.B64Util;
import fct.unl.pt.instagramplus.Utils.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.List;

import static fct.unl.pt.instagramplus.Services.Result.ErrorCode.*;
import static fct.unl.pt.instagramplus.Services.Result.error;
import static fct.unl.pt.instagramplus.Services.Result.ok;

@Service
public class AccountServiceClass implements AccountServiceInterface {

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private ProfileViewersRepository profileViewersRepository;

    @Autowired
    private FollowersRepository followersRepository;

    @Override
    public Result<Long> createAccount(Account account) {
        if(accountsRepository.existsAccountByEmailOrUsername(account.getEmail(), account.getUsername()))
            return error(CONFLICT);

        //HASH password + salt(email(unique))
        String b64PasswordHash = B64Util.encode(HashUtil.getHash(account.getPassword().getBytes()+account.getEmail()));
        account.setPassword(b64PasswordHash);

        Account acc = accountsRepository.save(account);
        return ok(acc.getId());
    }

    @Override
    public Result<Account> getAccount(Long id) {
        Account acc = accountsRepository.getAccountById(id);
        if(acc == null)
            return error(NOT_FOUND);
        return ok(acc);
    }

    @Override
    public Result<Void> deleteAccount(Long id) {
        Account acc = accountsRepository.getAccountById(id);
        if(acc == null)
            return error(NOT_FOUND);

        accountsRepository.deleteById(id);
        return ok();
    }

    @Override
    public Result<List<ProfileViewer>> getAccountViweres(Long id) {
        List<ProfileViewer> list = profileViewersRepository.getAllByProfileId(id);
        if(list == null)
            return error(NOT_FOUND);
        return ok(list);
    }

    @Override
    public Result<Void> followAccount(Follower follower) {
        Long fromId = follower.getAccountId();
        Long toId = follower.getIsFollowingId();
        Account fromAcc = accountsRepository.getAccountById(fromId);
        Account toAcc = accountsRepository.getAccountById(toId);
        if(fromAcc == null || toAcc == null)
            return error(NOT_FOUND);

        follower.setFollowDate();

        followersRepository.save(follower);
        return ok();
    }

    @Override
    public Result<Void> changeVisibility(Long accountId) {
        Account acc = accountsRepository.getAccountById(accountId);
        if(acc == null)
            return error(NOT_FOUND);
        acc.chengeVisibility();
        accountsRepository.save(acc);
        return ok();
    }
}
