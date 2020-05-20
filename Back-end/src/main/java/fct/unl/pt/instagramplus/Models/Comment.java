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
    Long id;

    @NotNull
    Long userId;

    @NotNull
    Long publicationId;
    @NotNull
    String comment;

    public Comment() { }

    public Comment(@NotNull Long userId, @NotNull Long publicationId,@NotNull String comment) {
        this.userId = userId;
        this.publicationId = publicationId;
        this.comment=comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(Long publicationId) {
        this.publicationId = publicationId;
    }
    //TODO colocar metodos do comentario
}
