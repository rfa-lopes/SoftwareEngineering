package fct.unl.pt.instagramplus.Models.Authenticator;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginModel {

    private String username;

    @NotNull
    private String password;

    private String email;

    public LoginModel() { }

    public LoginModel(String username, @NotNull String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
