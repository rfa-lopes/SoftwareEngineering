package fct.unl.pt.instagramplus.Models.Publications;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;
import java.net.URL;

@Entity
@Data
public class Content {

    @NotNull
    long publicationId;

    @NotNull
    byte[] content;

    public Content() { }

    public Content(long publicationId, byte[] content) {
        this.publicationId = publicationId;
        this.content = content;
    }

    public long getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(long publicationId) {
        this.publicationId = publicationId;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
