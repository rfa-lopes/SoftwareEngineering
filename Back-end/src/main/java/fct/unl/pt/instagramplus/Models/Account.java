package fct.unl.pt.instagramplus.Models;

import fct.unl.pt.instagramplus.Utils.DefaultImage;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.*;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;

    @NotNull
    @Lob
    private String profileImage; //Base64

    @NotNull
    private String name;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private boolean isPublic;

    public Account() { }

    public Account(@NotNull String username, @NotNull String password, @NotNull String name, @NotNull String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.isPublic = false;
        this.profileImage = DefaultImage.getInstance().get();
    }

    public Long getId() {
        return id;
    }
    public String getProfileImage(){
        return profileImage;
    }
    public void setProfileImage(String profileImage){
        this.profileImage=profileImage;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public void chengeVisibility(){
        isPublic = !isPublic;
    }

}
