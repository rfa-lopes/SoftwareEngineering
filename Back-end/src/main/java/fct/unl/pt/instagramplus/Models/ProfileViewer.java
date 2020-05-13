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
    long id;

    @NotNull
    long viewerId; //this guy...

    @NotNull
    long profileId; //see the profile of this guy

    @NotNull
    String viewDate;

    public ProfileViewer() { }

    public ProfileViewer(@NotNull long viewerId, @NotNull long profileId) {
        this.viewerId = viewerId;
        this.profileId = profileId;
        this.viewDate = DateUtil.getAtualDate();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getViewerId() {
        return viewerId;
    }

    public void setViewerId(long viewerId) {
        this.viewerId = viewerId;
    }

    public long getProfileId() {
        return profileId;
    }

    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }

    public String getViewDate() {
        return viewDate;
    }

    public void setViewDate(String viewDate) {
        this.viewDate = viewDate;
    }
}
