package fct.unl.pt.instagramplus.Services.MessagesServices;

import fct.unl.pt.instagramplus.Models.Message;
import fct.unl.pt.instagramplus.Services.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageServiceInterface {

    Result<Long> sendMessage(Message message);

    Result<Void> deleteMessage(Long messageId);

    Result<List<Message>> getAllMessagesFromConversation(Long accountId, Long toAccountId);

}
