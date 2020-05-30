package fct.unl.pt.instagramplus.Models;

import fct.unl.pt.instagramplus.Utils.DateUtil;
import fct.unl.pt.instagramplus.Utils.DefaultImage;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.AUTO;
@Entity
@Data
public class Stories {
    @Id
    @GeneratedValue(strategy = AUTO)
    Long id;

    @NotNull
    Long ownerId;

    @NotNull
    String publicationDate;

    String expireDate;

    @NotNull
    @Lob
    String image; //Base64
    public Stories(){}

    public Stories(@NotNull Long ownerId){
        this.ownerId = ownerId;
        this.publicationDate = DateUtil.getAtualDate();
        this.expireDate = DateUtil.addHoursToDate(publicationDate);
        this.image = DefaultImage.getInstance().getRandom();
    }

    public Stories(@NotNull Long ownerId, @NotNull String publicationDate, String expireDate){
        this.ownerId = ownerId;
        this.publicationDate = publicationDate;
        this.expireDate = expireDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

