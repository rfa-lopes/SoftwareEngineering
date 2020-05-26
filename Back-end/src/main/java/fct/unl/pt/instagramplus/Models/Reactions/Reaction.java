package fct.unl.pt.instagramplus.Models.Reactions;

import fct.unl.pt.instagramplus.Utils.DateUtil;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
public class Reaction {

    @Id
    @GeneratedValue(strategy = AUTO)
    long id;

    @NotNull
    long userId;

    @NotNull
    long publicationId;

    @NotNull
    String reactionDate;

    @NotNull
    ReactionType type;

    public Reaction(){}

    public Reaction(@NotNull long userId, @NotNull long publicationId,  @NotNull ReactionType type) {
        this.userId = userId;
        this.publicationId = publicationId;
        this.type = type;
        this.reactionDate = DateUtil.getAtualDate();
    }

    public long getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(long publicationId) {
        this.publicationId = publicationId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getReactionDate() {
        return reactionDate;
    }

    public void setReactionDate(String reactionDate) {
        this.reactionDate = reactionDate;
    }

    public ReactionType getType() {
        return type;
    }

    public void setType(ReactionType type) {
        this.type = type;
    }
}
