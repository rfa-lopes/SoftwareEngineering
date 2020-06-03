package fct.unl.pt.instagramplus.Controllers.Authenticator;

import fct.unl.pt.instagramplus.Models.Account;
import fct.unl.pt.instagramplus.Models.Wrapper;
import fct.unl.pt.instagramplus.Models.LoginModel;
import fct.unl.pt.instagramplus.Repositories.Accounts.AccountsRepository;
import fct.unl.pt.instagramplus.Utils.CookiesUtil;
import fct.unl.pt.instagramplus.Utils.JwtUtil;
import fct.unl.pt.instagramplus.Utils.Logger;
import fct.unl.pt.instagramplus.Utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestController
public class AuthenticatorClass implements AuthenticatorInterface {

    @Autowired
    private AccountsRepository accountsRepository;

    @Value("${userAuth}")
    private boolean useAuth;

    @Override
    public ResponseEntity<Wrapper<Account>> login(LoginModel login, HttpServletResponse resp) {
        Logger.info("Request: LOGIN");
        Account acc;
        if (login.getUsername() != null)
            acc = accountsRepository.getAccountByUsername(login.getUsername());
        else return ResponseEntity.status(BAD_REQUEST).build(); // Bab request

        if (acc == null || !PasswordUtil.verify(acc.getPassword(), login.getPassword()))
            return ResponseEntity.status(UNAUTHORIZED).build(); // Unauthorized

        String token = JwtUtil.createJWT(acc.getId());
        acc.setPassword(null); //Melhor solucao: criar um model sem password

        @SuppressWarnings("unchecked")
        Wrapper<Account> cw = new Wrapper.WrapperBuilder(acc).setCoookie(token).build();

        return ResponseEntity.ok(cw);
    }

    @Override
    public ResponseEntity<Void> logout(HttpServletRequest req, HttpServletResponse resp) {
        Logger.info("REQUEST: LOGOUT");
        if (useAuth) {
            Cookie token = CookiesUtil.getCookie("AuthToken", req.getCookies());

            if (token == null)
                return ResponseEntity.status(UNAUTHORIZED).build();

            token.setMaxAge(0);
            resp.addCookie(token);
        }
        return ResponseEntity.ok().build();
    }

}
