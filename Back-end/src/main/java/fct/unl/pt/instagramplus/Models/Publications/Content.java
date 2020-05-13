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
    String content; //BASE64

    public Content() { }

    public Content(@NotNull Long publicationId, @NotNull String content) {
        this.publicationId = publicationId;
        this.content = content;
    }

    public Long getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(Long publicationId) {
        this.publicationId = publicationId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
