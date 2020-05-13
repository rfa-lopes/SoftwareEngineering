package fct.unl.pt.instagramplus.Models;

import fct.unl.pt.instagramplus.Utils.DateUtil;
import lombok.Data;
import org.springframework.util.unit.DataUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
public class Follower {

    @Id
    @GeneratedValue(strategy = AUTO)
    Long id;

    @NotNull
    Long accountId;

    @NotNull
    Long isFollowingId;

    @NotNull
    String followDate;

    public Follower() { }

    public Follower(Long accountId, Long isFollowingId) {
        this.accountId = accountId;
        this.isFollowingId = isFollowingId;
        this.followDate = DateUtil.getAtualDate();
    }

    public Follower(Long accountId, Long isFollowingId, String followDate) {
        this.accountId = accountId;
        this.isFollowingId = isFollowingId;
        this.followDate = followDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFollowDate() {
        return followDate;
    }

    public void setFollowDate() {
        this.followDate = DateUtil.getAtualDate();
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getIsFollowingId() {
        return isFollowingId;
    }

    public void setIsFollowingId(Long isFollowingId) {
        this.isFollowingId = isFollowingId;
    }
}
