package fct.unl.pt.instagramplus.Models;

import fct.unl.pt.instagramplus.Utils.DateUtil;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
public class ProfileViewer {

    @Id
    @GeneratedValue(strategy = AUTO)
    Long id;

    @NotNull
    Long viewerId; //this guy...

    @NotNull
    Long profileId; //see the profile of this guy

    @NotNull
    String viewDate;

    public ProfileViewer() { }

    public ProfileViewer(@NotNull Long viewerId, @NotNull Long profileId) {
        this.viewerId = viewerId;
        this.profileId = profileId;
        this.viewDate = DateUtil.getAtualDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getViewerId() {
        return viewerId;
    }

    public void setViewerId(Long viewerId) {
        this.viewerId = viewerId;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public String getViewDate() {
        return viewDate;
    }

    public void setViewDate(String viewDate) {
        this.viewDate = viewDate;
    }
}
