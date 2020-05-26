package fct.unl.pt.instagramplus.Controllers.Authenticator;

import fct.unl.pt.instagramplus.Models.Account;
import fct.unl.pt.instagramplus.Models.Wrapper;
import fct.unl.pt.instagramplus.Models.LoginModel;
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

    /**
     * Login
     * @param login login model - username:password
     * @param resp add cookies to response
     * @return authenticated account + auth token
     */
    @PostMapping(
            value = LOGIN,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Wrapper<Account>> login(@RequestBody LoginModel login, HttpServletResponse resp);

    /**
     * Logout
     * @param req request to logout
     * @param resp response (delete cookie from browser)
     * @return void
     */
    @PostMapping(value = LOGOUT)
    ResponseEntity<Void> logout(HttpServletRequest req, HttpServletResponse resp);
}
