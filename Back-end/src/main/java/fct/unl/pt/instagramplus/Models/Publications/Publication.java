package fct.unl.pt.instagramplus.Models.Publications;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
public class Publication {

    @Id
    @GeneratedValue(strategy = AUTO)
    long id;

    @NotNull
    long ownerId;

    @NotNull
    String publicationDate;

    String expireDate;

    @NotNull
    String description;

    public Publication(){}

    public Publication(@NotNull long ownerId, @NotNull String publicationDate, String expireDate, String description) {
        this.ownerId = ownerId;
        this.publicationDate = publicationDate;
        this.expireDate = expireDate;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }
}
