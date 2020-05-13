package fct.unl.pt.instagramplus.Controllers.Accounts;


import fct.unl.pt.instagramplus.Models.Accounts.Account;
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

    String CREATE = "/create";
    String GET = "/get/{id}";
    String DELETE = "/delete/{id}";
    String VIWERES = "/viweres/{id}";
    String CHANGEVISIBILITY = "/change/{id}";
    String FOLLOW = "/follow";
    String UNFOLLOW = "/unfollow";
    String FOLLOWERS = "/followers/{id}";
    String FOLLOWINGS = "/followings/{id}";

    /**
     * Create account
     * @param account accont to create
     * @return account id
     */
    @PostMapping(
            value = CREATE,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Long> createAccount(
            @RequestBody Account account);

    /**
     * Get account by id
     * @param id
     * @return account
     */
    @GetMapping(
            value = GET,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Account> gettAccount(
            @PathVariable( "id" ) Long id);

    /**
     * Delete account by id
     * @param id account id
     * @return void
     */
    @DeleteMapping(
            value = DELETE,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> deleteAccount(
            @PathVariable( "id" ) Long id);


    /**
     * List account how viewer account id
     * @param id account id
     * @return list of accounts
     */
    @GetMapping(
            value = VIWERES,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProfileViewer>> gettAccountViweres(
            @PathVariable( "id" ) Long id);


    /**
     * Change account visibility
     * @param id account id
     * @return void
     */
    @GetMapping(value = CHANGEVISIBILITY)
    ResponseEntity<Void> changeAccountVisibility(
            @PathVariable Long id);

    /**
     * Follow account
     * @param follower
     * @return void
     */
    @PostMapping(
            value = FOLLOW,
            consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> followAccount(
            @RequestBody Follower follower);

    /**
     * Unfollow account
     * @param follower
     * @return void
     */
    @PostMapping(
            value = UNFOLLOW,
            consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> unfollowAccount(
            @RequestBody Follower follower);

    /**
     * Get account followers
     * @param id
     * @return followers list
     */
    @GetMapping(
            value = FOLLOWERS)
    ResponseEntity<List<Follower>> getFollowersAccount(
            @PathVariable Long id);

    /**
     * Obter quem o id segue
     * @param id
     * @return followings list
     */
    @GetMapping(
            value = FOLLOWINGS)
    ResponseEntity<List<Follower>> getAccountFollowings(
            @PathVariable Long id);


}

