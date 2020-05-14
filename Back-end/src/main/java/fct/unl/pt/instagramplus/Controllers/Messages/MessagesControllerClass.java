package fct.unl.pt.instagramplus.Controllers.Messages;

import fct.unl.pt.instagramplus.Controllers.Response;
import fct.unl.pt.instagramplus.Models.Messages.Message;
import fct.unl.pt.instagramplus.Repositories.Messages.MessagesRepository;
import fct.unl.pt.instagramplus.Services.MessagesServices.MessageServiceClass;
import fct.unl.pt.instagramplus.Utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestController
public class MessagesControllerClass implements MessagesControllerInterface{

    @Autowired
    MessageServiceClass messageServiceClass;

    @Autowired
    MessagesRepository messagesRepository;

    @Override
    public ResponseEntity<Long> sendMessage(Long accountRequestId, Message message) {
        Logger.info("Request: SEND MESSAGE");
        if(!accountRequestId.equals(message.getFromUserId()))
            return ResponseEntity.status(UNAUTHORIZED).build();
        return Response.resultOrErrorCode(messageServiceClass.sendMessage(message));
    }

    @Override
    public ResponseEntity<Void> deleteMessage(Long accountRequestId, Long messageId) {
        Logger.info("Request: DELETE MESSAGE");
        Long fromUser = messagesRepository.getMessagenById(messageId).getFromUserId();
        if(!accountRequestId.equals(fromUser)) //Só pode apagar as mensagens que escreveu
            return ResponseEntity.status(UNAUTHORIZED).build();
        return Response.resultOrErrorCode(messageServiceClass.deleteMessage(messageId));
    }

    @Override
    public ResponseEntity<List<Message>> getAllMessagesFromConversation(Long accountRequestId, Long fromAccountId, Long toAccountId) {
        Logger.info("Request: GET ALL MESSAGES");
        if(!accountRequestId.equals(fromAccountId))
            return ResponseEntity.status(UNAUTHORIZED).build();
        return Response.resultOrErrorCode(messageServiceClass.getAllMessagesFromConversation(fromAccountId, toAccountId));
    }


}
