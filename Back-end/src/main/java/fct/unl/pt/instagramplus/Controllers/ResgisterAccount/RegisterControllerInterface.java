package fct.unl.pt.instagramplus.Controllers.ResgisterAccount;

import fct.unl.pt.instagramplus.Models.Accounts.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = RegisterControllerInterface.BASE_URL)
public interface RegisterControllerInterface {

    String BASE_URL = "/register";

    String CREATE = "/account";

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
}
