package fct.unl.pt.instagramplus.Controllers.Authenticator;

import fct.unl.pt.instagramplus.Models.Accounts.Account;
import fct.unl.pt.instagramplus.Models.Authenticator.LoginModel;
import fct.unl.pt.instagramplus.Repositories.Accounts.AccountsRepository;
import fct.unl.pt.instagramplus.Utils.CookiesUtil;
import fct.unl.pt.instagramplus.Utils.JwtUtil;
import fct.unl.pt.instagramplus.Utils.Logger;
import fct.unl.pt.instagramplus.Utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthenticatorClass implements AuthenticatorInterface{

    @Autowired
    private AccountsRepository accountsRepository;

    @Override
    public ResponseEntity<Account> login(LoginModel login, HttpServletResponse resp) {
        Logger.info("Request: LOGIN");
        Account acc;
        if(login.getEmail() != null)
            acc = accountsRepository.getAccountByEmail(login.getEmail());
        else if(login.getUsername() != null)
            acc = accountsRepository.getAccountByUsername(login.getUsername());
        else return ResponseEntity.status(400).build(); // Bab request

        if(acc == null)
            return ResponseEntity.status(401).build(); // Unauthorized

        if(!PasswordUtil.verify(acc.getPassword(), login.getPassword()))
            return ResponseEntity.status(401).build(); // Unauthorized

        String token = JwtUtil.createJWT(acc.getId());
        resp.addCookie(new Cookie(AuthenticatorInterface.TOKEN_NAME, token));
        acc.setPassword(null);
        return ResponseEntity.ok(acc);
    }

    @Override
    public ResponseEntity<Void> logout(HttpServletRequest req, HttpServletResponse resp) {
        Logger.info("REQUEST: LOGOUT");
        Cookie token = CookiesUtil.getCookie("AuthToken", req.getCookies());

        if(token == null)
            return ResponseEntity.badRequest().build();

        token.setMaxAge(0);
        resp.addCookie(token);
        return ResponseEntity.ok().build();
    }

}
