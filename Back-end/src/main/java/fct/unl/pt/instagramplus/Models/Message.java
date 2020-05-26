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
public class Message {

    @Id
    @GeneratedValue(strategy = AUTO)
    Long id;

    @NotNull
    Long fromUserId;

    @NotNull
    Long toUserId;

    @NotNull
    String messageText;

    String sendedDate;

    String receivedDate;

    public Message() { }

    public Message(@NotNull Long fromUserId, @NotNull Long toUserId, @NotNull String messageText) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.messageText = messageText;
        this.sendedDate = DateUtil.getAtualDate();
    }


    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public void setSendedDate(String sendedDate) {
        this.sendedDate = sendedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
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

    public void setSendedDate() {
        this.sendedDate = DateUtil.getAtualDate();
    }

    public String getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate() {
        this.receivedDate = DateUtil.getAtualDate();
    }

}
