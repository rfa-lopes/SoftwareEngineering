package fct.unl.pt.instagramplus.Controllers.Messages;

import fct.unl.pt.instagramplus.Models.Accounts.Account;
import fct.unl.pt.instagramplus.Models.Messages.Conversation;
import fct.unl.pt.instagramplus.Models.Messages.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = MessagesControllerInterface.BASE_URL)
public interface MessagesControllerInterface {

    String BASE_URL = "/messages";

    String SEND = "/sendmessage";
    String DELETE = "/detetemessage";
    String GET_ALL_MESSAGES_FROM_CONVERSATION = "/allmessages";
    String GET_ALL_CONVERSATIONS_FROM_ACCOUNT = "/allconversations";

    @PostMapping(
            value = SEND,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Long> sendMessage(
            @RequestBody Message message);

    @DeleteMapping(
            value = DELETE + "/{id}",
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> deleteMessage(
            @PathVariable( "id" ) Long messageId);

    @GetMapping(
            value = GET_ALL_MESSAGES_FROM_CONVERSATION + "/{id}",
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<Message>> getAllMessagesFromConversation(
            @PathVariable( "id" ) Long conversationId);

    @GetMapping(
            value = GET_ALL_CONVERSATIONS_FROM_ACCOUNT + "/{id}",
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<Conversation>> getAllConversationsFromAccount(
            @PathVariable( "id" ) Long accountId);

}
