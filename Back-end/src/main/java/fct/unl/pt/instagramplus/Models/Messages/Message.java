package fct.unl.pt.instagramplus.Models.Messages;

import fct.unl.pt.instagramplus.Utils.DateUtil;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = AUTO)
    long id;

    @NotNull
    long conversationId;

    @NotNull
    long fromUserId;

    @NotNull
    String messageText;

    String sendedDate;

    String receivedDate;

    String readedDate;

    public Message() { }

    public Message(@NotNull long conversationId, @NotNull long fromUserId, @NotNull String messageText) {
        this.conversationId = conversationId;
        this.fromUserId = fromUserId;
        this.messageText = messageText;
        this.sendedDate = DateUtil.getAtualDate();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getConversationId() {
        return conversationId;
    }

    public void setConversationId(long conversationId) {
        this.conversationId = conversationId;
    }

    public long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getSendedDate() {
        return sendedDate;
    }

    public void setSendedDate(String sendedDate) {
        this.sendedDate = sendedDate;
    }

    public String getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getReadedDate() {
        return readedDate;
    }

    public void setReadedDate(String readedDate) {
        this.readedDate = readedDate;
    }
}
