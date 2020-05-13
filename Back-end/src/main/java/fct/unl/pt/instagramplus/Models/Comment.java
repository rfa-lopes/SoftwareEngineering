package fct.unl.pt.instagramplus.Models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = AUTO)
    long id;

    @NotNull
    long userId;

    @NotNull
    long publicationId;

    public Comment() { }

    public Comment(@NotNull long userId, @NotNull long publicationId) {
        this.userId = userId;
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

    public long getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(long publicationId) {
        this.publicationId = publicationId;
    }
}
