package fct.unl.pt.instagramplus.Models.Publications;

import fct.unl.pt.instagramplus.Utils.DateUtil;
import fct.unl.pt.instagramplus.Utils.DefaultImage;
import lombok.Data;
import org.springframework.util.unit.DataUnit;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
public class Publication {

    @Id
    @GeneratedValue(strategy = AUTO)
    Long id;

    @NotNull
    Long ownerId;

    @NotNull
    String publicationDate;

    String expireDate;


    String description;

    @NotNull
    @Lob
    String image; //Base64

    public Publication() {
    }

    public Publication(@NotNull Long ownerId, @NotNull String publicationDate, String expireDate, String description) {
        this.ownerId = ownerId;
        this.publicationDate = publicationDate;
        this.expireDate = expireDate;
        this.description = description;
    }

    public Publication(Long ownerId, String description) {
        this.ownerId = ownerId;
        this.description = description;
        this.publicationDate = DateUtil.getAtualDate();
        this.image = DefaultImage.getInstance().get();
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
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
