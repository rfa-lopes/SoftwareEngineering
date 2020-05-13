package fct.unl.pt.instagramplus.Controllers.Accounts;


import fct.unl.pt.instagramplus.Models.Accounts.Account;
import fct.unl.pt.instagramplus.Models.ProfileViewer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = AccountsControllerInterface.ACCOUNTS_BASE_URL)
public interface AccountsControllerInterface {

    String ACCOUNTS_BASE_URL = "/accounts";

    String CREATE = "/create";
    String GET = "/get";
    String DELETE = "/detete";
    String VIWERES = "/viweres";

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
            value = GET + "/{id}",
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Account> gettAccount(
            @PathVariable( "id" ) Long id);

    /**
     * Delete account by id
     * @param id account id
     * @return void
     */
    @DeleteMapping(
            value = DELETE + "/{id}",
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> deleteAccount(
            @PathVariable( "id" ) Long id);


    /**
     * List account how viewer account id
     * @param id account id
     * @return list of accounts
     */
    @GetMapping(
            value = VIWERES + "/{id}",
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProfileViewer>> gettAccountViweres(
            @PathVariable( "id" ) Long id);
}
