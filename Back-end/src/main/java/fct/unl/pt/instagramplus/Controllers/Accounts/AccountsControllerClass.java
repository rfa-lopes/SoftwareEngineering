package fct.unl.pt.instagramplus.Controllers.Accounts;

import fct.unl.pt.instagramplus.Controllers.Response;
import fct.unl.pt.instagramplus.Models.Accounts.Account;
import fct.unl.pt.instagramplus.Models.ProfileViewer;
import fct.unl.pt.instagramplus.Services.AccountsServices.AccountServiceClass;
import fct.unl.pt.instagramplus.Utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountsControllerClass implements AccountsControllerInterface{

    @Autowired
    AccountServiceClass accountService;

    @Override
    public ResponseEntity<Long> createAccount(Account account) {
        Logger.info("Request: CREATEACCOUNT");
        return Response.resultOrErrorCode(accountService.createAccount(account));
    }

    @Override
    public ResponseEntity<Account> gettAccount(Long id) {
        Logger.info("Request: GETACCOUNT");
        return Response.resultOrErrorCode(accountService.getAccount(id));
    }

    @Override
    public ResponseEntity<Void> deleteAccount(Long id) {
        Logger.info("Request: DELETEACCOUNT");
        return Response.resultOrErrorCode(accountService.deteleAccount(id));
    }

    @Override
    public ResponseEntity<List<ProfileViewer>> gettAccountViweres(Long id) {
        Logger.info("Request: ACCOUNTVIWERES");
        return Response.resultOrErrorCode(accountService.gettAccountViweres(id));
    }
}
