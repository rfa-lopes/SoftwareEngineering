package fct.unl.pt.instagramplus.Models.Publications;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.net.URL;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
public class Content {

    @Id
    @GeneratedValue(strategy = AUTO)
    Long id;

    @NotNull
    Long publicationId;

    @NotNull
    byte[] content;

    public Content() { }

    public Content(@NotNull Long publicationId, @NotNull byte[] content) {
        this.publicationId = publicationId;
        this.content = content;
    }

    public Long getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(Long publicationId) {
        this.publicationId = publicationId;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
