package fct.unl.pt.instagramplus.Controllers.Accounts;

import fct.unl.pt.instagramplus.Controllers.Response;
import fct.unl.pt.instagramplus.Models.Account;
import fct.unl.pt.instagramplus.Models.Follower;
import fct.unl.pt.instagramplus.Models.ProfileViewer;
import fct.unl.pt.instagramplus.Models.Publications.Publication;
import fct.unl.pt.instagramplus.Models.Stories;
import fct.unl.pt.instagramplus.Services.AccountsServices.AccountServiceClass;
import fct.unl.pt.instagramplus.Utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestController
public class AccountsControllerClass implements AccountsControllerInterface {

    @Autowired
    AccountServiceClass accountService;

    @Value("${userAuth}")
    private boolean useAuth;

    @Override
    public ResponseEntity<Account> getAccount(Long accountRequestId, Long id) {
        Logger.info("Request: GET ACCOUNT BY: " + id);
        accountService.setNewViewer(accountRequestId, id);
        return Response.resultOrErrorCode(accountService.getAccount(id));
    }

    @Override
    public ResponseEntity<Void> deleteAccount(Long accountRequestId, Long id) {
        Logger.info("Request: DELETE ACCOUNT BY: " + accountRequestId);
        if (useAuth && !accountRequestId.equals(id))
            return ResponseEntity.status(UNAUTHORIZED).build();
        return Response.resultOrErrorCode(accountService.deleteAccount(id));
    }

    @Override
    public ResponseEntity<List<ProfileViewer>> getAccountViweres(Long accountRequestId, Long id) {
        Logger.info("Request: ACCOUNT VIEWERS BY: " + accountRequestId);
        if (useAuth && !accountRequestId.equals(id))
            return ResponseEntity.status(UNAUTHORIZED).build();
        return Response.resultOrErrorCode(accountService.getAccountViweres(id));
    }

    @Override
    public ResponseEntity<Void> followAccount(Long accountRequestId, Follower follower) {
        Logger.info("Request: FOLLOW ACCOUNT BY: " + accountRequestId);
        if (useAuth && !accountRequestId.equals(follower.getAccountId()))
            return ResponseEntity.status(UNAUTHORIZED).build();
        return Response.resultOrErrorCode(accountService.followAccount(follower));
    }

    @Override
    public ResponseEntity<Void> unfollowAccount(Long accountRequestId, Follower follower) {
        Logger.info("Request: UNFOLLOW ACCOUNT BY: " + accountRequestId);
        if (useAuth && !accountRequestId.equals(follower.getAccountId()))
            return ResponseEntity.status(UNAUTHORIZED).build();
        return Response.resultOrErrorCode(accountService.unfollowAccount(follower));
    }

    @Override
    public ResponseEntity<List<Follower>> getFollowersAccount(Long accountRequestId, Long id) {
        Logger.info("Request: ACCOUNT FOLLOWERS BY: " + accountRequestId);
        if (useAuth && !accountRequestId.equals(id))
            return ResponseEntity.status(UNAUTHORIZED).build();
        return Response.resultOrErrorCode(accountService.getFollowersAccount(id));
    }

    @Override
    public ResponseEntity<List<Follower>> getAccountFollowings(Long accountRequestId, Long id) {
        Logger.info("Request: ACCOUNT FOLLOWINGS BY: " + accountRequestId);
        if (useAuth && !accountRequestId.equals(id))
            return ResponseEntity.status(UNAUTHORIZED).build();
        return Response.resultOrErrorCode(accountService.getAccountFollowings(id));
    }

    @Override
    public ResponseEntity<Account> getAccountViewer(Long accountRequestId, Long id) {
        Logger.info("Request: GET ACCOUNT VIEWER BY: " + accountRequestId);
        if (useAuth && !accountRequestId.equals(id))
            return ResponseEntity.status(UNAUTHORIZED).build();
        return Response.resultOrErrorCode(accountService.getAccount(id));
    }

    @Override
    public ResponseEntity<List<Publication>> getAccountFeed(Long accountRequestId, Long id) {
        Logger.info("Request: GET FEED BY: " + accountRequestId);
        if (useAuth && !accountRequestId.equals(id))
            return ResponseEntity.status(UNAUTHORIZED).build();
        return Response.resultOrErrorCode(accountService.getFeed(id));
    }

    @Override
    public ResponseEntity<Map<Long, List<Stories>>> getStoryFeed(Long accountRequestId, Long id) {
        Logger.info("Request: GET STORY FEED BY: " + accountRequestId);
        if (useAuth && !accountRequestId.equals(id))
            return ResponseEntity.status(UNAUTHORIZED).build();
        return Response.resultOrErrorCode(accountService.getStoryFeed(id));
    }

    @Override
    public ResponseEntity<Void> changeAccountVisibility(Long accountRequestId, Long id) {
        Logger.info("Request: CHANGE VISIBILITY BY: " + accountRequestId);
        if (useAuth && !accountRequestId.equals(id))
            return ResponseEntity.status(UNAUTHORIZED).build();
        return Response.resultOrErrorCode(accountService.changeVisibility(id));
    }


}
