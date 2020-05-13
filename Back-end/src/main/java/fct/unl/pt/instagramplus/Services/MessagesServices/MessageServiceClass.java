package fct.unl.pt.instagramplus.Services.MessagesServices;

import fct.unl.pt.instagramplus.Models.Accounts.Account;
import fct.unl.pt.instagramplus.Models.Messages.AccountInConversation;
import fct.unl.pt.instagramplus.Models.Messages.Conversation;
import fct.unl.pt.instagramplus.Models.Messages.Message;
import fct.unl.pt.instagramplus.Repositories.Accounts.AccountsRepository;
import fct.unl.pt.instagramplus.Repositories.Messages.AccountsInConversationRepository;
import fct.unl.pt.instagramplus.Repositories.Messages.ConversationsRepository;
import fct.unl.pt.instagramplus.Repositories.Messages.MessagesRepository;
import fct.unl.pt.instagramplus.Services.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

import static fct.unl.pt.instagramplus.Services.Result.ErrorCode.*;
import static fct.unl.pt.instagramplus.Services.Result.error;
import static fct.unl.pt.instagramplus.Services.Result.ok;

@Service
public class MessageServiceClass implements MessageServiceInterface{

    @Autowired
    MessagesRepository messagesRepository;

    @Autowired
    ConversationsRepository conversationsRepository;

    @Autowired
    AccountsRepository accountsRepository;

    @Autowired
    AccountsInConversationRepository accountsInConversationRepository;

    @Override
    public Result<Long> sendMessage(Message message) {
        Long conversatioId = message.getConversationId();
        Conversation con = conversationsRepository.getConversationById(conversatioId);
        if(con == null)
            return error(NOT_FOUND);
        Long fromAccountId = message.getFromUserId();
        Account acc = accountsRepository.getAccountById(fromAccountId);
        if(acc == null)
            return error(NOT_FOUND);
        message.setSendedDate();
        Message mes = messagesRepository.save(message);
        return ok(mes.getId());
    }

    @Override
    public Result<Void> deleteMessage(Long messageId) {
        Message mes = messagesRepository.getMessagenById(messageId);
        if(mes == null)
            return error(NOT_FOUND);
        messagesRepository.deleteById(messageId);
        return ok();
    }

    @Override
    public Result<List<Message>> getAllMessagesFromConversation(Long conversationId) {
        Conversation con = conversationsRepository.getConversationById(conversationId);
        if(con == null)
            return error(NOT_FOUND);
        List<Message> messages = messagesRepository.getAllByConversationId(conversationId);
        return ok(messages);
    }

    @Override
    public Result<List<Conversation>> getAllConversationsFromAccount(Long accountId) {
        Account acc = accountsRepository.getAccountById(accountId);
        if(acc == null)
            return error(NOT_FOUND);
        List<AccountInConversation> listAccountsInConversation = accountsInConversationRepository.getAllByAccountId(accountId);
        List<Conversation> list = new LinkedList<>();
        for(AccountInConversation accInCon : listAccountsInConversation)
            list.add(conversationsRepository.getConversationById(accInCon.getConversationId()));
        return ok(list);
    }
}

