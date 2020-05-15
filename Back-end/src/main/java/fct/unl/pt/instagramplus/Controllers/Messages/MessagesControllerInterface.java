package fct.unl.pt.instagramplus.Controllers.Messages;

import fct.unl.pt.instagramplus.Models.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = MessagesControllerInterface.BASE_URL)
public interface MessagesControllerInterface {

    String BASE_URL = "/messages";

    String SEND = "/sendmessage";
    String DELETE = "/detetemessage/{id}";
    String GET_ALL_MESSAGES_FROM_CONVERSATION = "/allmessages/{fromIid}/{toId}";

    /**
     * Send message to other user
     * @param accountRequestId Authenticated user
     * @param message message to send
     * @return message id
     */
    @PostMapping(
            value = SEND,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Long> sendMessage(
            @RequestAttribute("id") Long accountRequestId,
            @RequestBody Message message);

    /**
     *
     * @param accountRequestId Authenticated user
     * @param messageId message id to delete
     * @return void
     */
    @DeleteMapping(
            value = DELETE,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> deleteMessage(
            @RequestAttribute("id") Long accountRequestId,
            @PathVariable( "id" ) Long messageId);

    /**
     *
     * @param accountRequestId Authenticated user
     * @param fromAccountId from user
     * @param toAccountId to user
     * @return all messages from one user to onother
     */
    @GetMapping(
            value = GET_ALL_MESSAGES_FROM_CONVERSATION,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<Message>> getAllMessagesFromConversation(
            @RequestAttribute("id") Long accountRequestId,
            @PathVariable( "fromIid" ) Long fromAccountId, @PathVariable( "toId" )Long toAccountId);

}
