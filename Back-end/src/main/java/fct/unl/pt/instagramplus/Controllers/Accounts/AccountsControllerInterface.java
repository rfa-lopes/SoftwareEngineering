package fct.unl.pt.instagramplus.Controllers.Accounts;


import fct.unl.pt.instagramplus.Models.Account;
import fct.unl.pt.instagramplus.Models.Follower;
import fct.unl.pt.instagramplus.Models.ProfileViewer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = AccountsControllerInterface.BASE_URL)
public interface AccountsControllerInterface {

    String BASE_URL = "/accounts";

    String GET = "/get/{id}";
    String DELETE = "/delete/{id}";
    String VIWERES = "/viweres/{id}";
    String CHANGEVISIBILITY = "/change/{id}";
    String FOLLOW = "/follow";
    String UNFOLLOW = "/unfollow";
    String FOLLOWERS = "/followers/{id}";
    String FOLLOWINGS = "/followings/{id}";
    String ACCOUNTVIEWER = "/getviewer/{id}";

    /**
     * Get account by id
     * @param accountRequestId Authenticated user
     * @param id
     * @return account
     */
    @GetMapping(
            value = GET,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Account> getAccount(
            @RequestAttribute("id") Long accountRequestId,
            @PathVariable( "id" ) Long id);

    /**
     * Delete account by id
     * @param accountRequestId Authenticated user
     * @param id account id
     * @return void
     */
    @DeleteMapping(
            value = DELETE)
    ResponseEntity<Void> deleteAccount(
            @RequestAttribute("id") Long accountRequestId,
            @PathVariable( "id" ) Long id);


    /**
     * List account how viewer account id
     * @param accountRequestId Authenticated user
     * @param id account id
     * @return list of accounts
     */
    @GetMapping(
            value = VIWERES,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProfileViewer>> getAccountViweres(
            @RequestAttribute("id") Long accountRequestId,
            @PathVariable( "id" ) Long id);


    /**
     * Change account visibility
     * @param accountRequestId Authenticated user
     * @param id account id
     * @return void
     */
    @GetMapping(value = CHANGEVISIBILITY)
    ResponseEntity<Void> changeAccountVisibility(
            @RequestAttribute("id") Long accountRequestId,
            @PathVariable Long id);

    /**
     * Follow account
     * @param accountRequestId Authenticated user
     * @param follower
     * @return void
     */
    @PostMapping(
            value = FOLLOW,
            consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> followAccount(
            @RequestAttribute("id") Long accountRequestId,
            @RequestBody Follower follower);

    /**
     * Unfollow account
     * @param accountRequestId Authenticated user
     * @param follower
     * @return void
     */
    @PostMapping(
            value = UNFOLLOW,
            consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> unfollowAccount(
            @RequestAttribute("id") Long accountRequestId,
            @RequestBody Follower follower);

    /**
     * Get account followers
     * @param accountRequestId Authenticated user
     * @param id
     * @return followers list
     */
    @GetMapping(
            value = FOLLOWERS)
    ResponseEntity<List<Follower>> getFollowersAccount(
            @RequestAttribute("id") Long accountRequestId,
            @PathVariable Long id);

    /**
     * Obter quem o id segue
     * @param accountRequestId Authenticated user
     * @param id
     * @return followings list
     */
    @GetMapping(
            value = FOLLOWINGS)
    ResponseEntity<List<Follower>> getAccountFollowings(
            @RequestAttribute("id") Long accountRequestId,
            @PathVariable Long id);


    @GetMapping(
            value = ACCOUNTVIEWER,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Account> getAccountViewer(
            @RequestAttribute("id") Long accountRequestId,
            @PathVariable( "id" ) Long id);


}

