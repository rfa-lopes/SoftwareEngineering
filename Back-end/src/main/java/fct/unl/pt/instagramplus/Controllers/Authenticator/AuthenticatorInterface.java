package fct.unl.pt.instagramplus.Controllers.Authenticator;

import fct.unl.pt.instagramplus.Models.Authenticator.AuthAccount;
import fct.unl.pt.instagramplus.Models.Authenticator.LoginModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = AuthenticatorInterface.BASE_URL)
public interface AuthenticatorInterface {

    String BASE_URL = "/auth";

    String LOGIN = "/login";
    String LOGOUT = "/logout";

    @PostMapping(
            value = LOGIN,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<AuthAccount> login(@RequestBody LoginModel login);

    @PostMapping(value = LOGOUT)
    ResponseEntity<Void> logout(HttpServletRequest req);
}
