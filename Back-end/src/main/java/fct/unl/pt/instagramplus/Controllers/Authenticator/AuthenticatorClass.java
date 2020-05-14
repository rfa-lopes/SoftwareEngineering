package fct.unl.pt.instagramplus.Controllers.Authenticator;

import fct.unl.pt.instagramplus.Models.Accounts.Account;
import fct.unl.pt.instagramplus.Models.Authenticator.AuthAccount;
import fct.unl.pt.instagramplus.Models.Authenticator.LoginModel;
import fct.unl.pt.instagramplus.Repositories.Accounts.AccountsRepository;
import fct.unl.pt.instagramplus.Utils.JwtUtil;
import fct.unl.pt.instagramplus.Utils.Logger;
import fct.unl.pt.instagramplus.Utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthenticatorClass implements AuthenticatorInterface{

    @Autowired
    private AccountsRepository accountsRepository;

    @Override
    public ResponseEntity<AuthAccount> login(LoginModel login) {

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
        AuthAccount aut = new AuthAccount(acc, token);
        return ResponseEntity.ok(aut);
    }

    @Override
    public ResponseEntity<Void> logout(HttpServletRequest req) {
        Logger.info("REQUEST: TESTS");
        return ResponseEntity.ok().build();
    }

}
