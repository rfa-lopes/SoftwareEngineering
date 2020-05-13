package fct.unl.pt.instagramplus.Models.Messages;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
public class AccountInConversation {

    @Id
    @GeneratedValue(strategy = AUTO)
    Long id;

    @NotNull
    Long conversationId;

    @NotNull
    Long accountId;

    public AccountInConversation() { }

    public AccountInConversation(@NotNull Long conversationId, @NotNull Long accountId) {
        this.conversationId = conversationId;
        this.accountId = accountId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getConversationId() {
        return conversationId;
    }

    public void setConversationId(Long conversationId) {
        this.conversationId = conversationId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
