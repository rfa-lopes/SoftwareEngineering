package fct.unl.pt.instagramplus.Models.Accounts;

import javax.validation.constraints.NotNull;
import java.net.URL;

public class User extends Account {

    private String name;
    private String email;
    private boolean isPublic;
    private URL profilePicture;

    public User(@NotNull String username, @NotNull String password, @NotNull String name, @NotNull String email) {
        super(username, password);
        this.name = name;
        this.email = email;
        this.isPublic = false;
        this.profilePicture = profilePicture;
    }
}
