package fct.unl.pt.instagramplus.Models.Accounts;

import fct.unl.pt.instagramplus.Utils.B64Util;
import fct.unl.pt.instagramplus.Utils.DateUtil;
import fct.unl.pt.instagramplus.Utils.HashUtil;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import java.text.DateFormat;

import static javax.persistence.GenerationType.*;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;

    @NotNull
    private String username;

    @NotNull
    @Lob
    private String hashpassword;

    @NotNull
    private String registerDate;

    @NotNull
    private boolean isLogin;

    public Account() { }

    public Account(@NotNull String username, @NotNull String password) {
        this.username = username;
        this.hashpassword = B64Util.encode(HashUtil.getHash(password));
        this.registerDate = DateUtil.getAtualDate();
        this.isLogin = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashpassword() {
        return hashpassword;
    }

    public void setHashpassword(String hashpassword) {
        this.hashpassword = hashpassword;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

}
