package fct.unl.pt.instagramplus.Controllers.Messages;

import fct.unl.pt.instagramplus.Controllers.Response;
import fct.unl.pt.instagramplus.Models.Messages.Conversation;
import fct.unl.pt.instagramplus.Models.Messages.Message;
import fct.unl.pt.instagramplus.Services.MessagesServices.MessageServiceClass;
import fct.unl.pt.instagramplus.Utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessagesControllerClass implements MessagesControllerInterface{

    @Autowired
    MessageServiceClass messageServiceClass;

    @Override
    public ResponseEntity<Long> sendMessage(Message message) {
        Logger.info("Request: SENDMESSAGE");
        return Response.resultOrErrorCode(messageServiceClass.sendMessage(message));
    }

    @Override
    public ResponseEntity<Void> deleteMessage(Long messageId) {
        Logger.info("Request: DELETEMESSAGE");
        return Response.resultOrErrorCode(messageServiceClass.deleteMessage(messageId));
    }

    @Override
    public ResponseEntity<List<Message>> getAllMessagesFromConversation(Long conversationId) {
        Logger.info("Request: GETALLMESSAGES");
        return Response.resultOrErrorCode(messageServiceClass.getAllMessagesFromConversation(conversationId));
    }

    @Override
    public ResponseEntity<List<Conversation>> getAllConversationsFromAccount(Long accountId) {
        Logger.info("Request: GETALLCONVERSATIONS");
        return Response.resultOrErrorCode(messageServiceClass.getAllConversationsFromAccount(accountId));
    }


}
