package fct.unl.pt.instagramplus.Models.Authenticator;

import fct.unl.pt.instagramplus.Models.Accounts.Account;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AuthAccount {

    @NotNull
    Account acc;

    @NotNull
    String token;

    public AuthAccount() { }

    public AuthAccount(@NotNull Account acc, @NotNull String token) {
        this.acc = acc;
        this.token = token;
    }

    public Account getAcc() {
        return acc;
    }

    public void setAcc(Account acc) {
        this.acc = acc;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
