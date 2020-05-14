package fct.unl.pt.instagramplus.Controllers.ResgisterAccount;

import fct.unl.pt.instagramplus.Controllers.Response;
import fct.unl.pt.instagramplus.Models.Accounts.Account;
import fct.unl.pt.instagramplus.Services.AccountsServices.AccountServiceClass;
import fct.unl.pt.instagramplus.Utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterControllerClass implements RegisterControllerInterface{

    @Autowired
    AccountServiceClass accountService;

    @Override
    public ResponseEntity<Long> createAccount(Account account) {
        Logger.info("Request: REGISTER ACCOUNT");
        return Response.resultOrErrorCode(accountService.createAccount(account));
    }
}
