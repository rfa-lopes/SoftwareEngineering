package fct.unl.pt.instagramplus.Controllers.Authenticator;

import fct.unl.pt.instagramplus.Models.Accounts.Account;
import fct.unl.pt.instagramplus.Models.Authenticator.LoginModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = AuthenticatorInterface.BASE_URL)
public interface AuthenticatorInterface {

    String BASE_URL = "/auth";

    String LOGIN = "/login";
    String LOGOUT = "/logout";

    String TOKEN_NAME = "AuthToken";

    @PostMapping(
            value = LOGIN,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Account> login(@RequestBody LoginModel login, HttpServletResponse resp);

    @PostMapping(value = LOGOUT)
    ResponseEntity<Void> logout(HttpServletRequest req, HttpServletResponse resp);
}
