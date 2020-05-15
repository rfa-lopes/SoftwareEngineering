package fct.unl.pt.instagramplus.Services.MessagesServices;

import fct.unl.pt.instagramplus.Models.Message;
import fct.unl.pt.instagramplus.Repositories.Accounts.AccountsRepository;
import fct.unl.pt.instagramplus.Repositories.MessagesRepository;
import fct.unl.pt.instagramplus.Services.Result;
import fct.unl.pt.instagramplus.Utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static fct.unl.pt.instagramplus.Services.Result.ErrorCode.*;
import static fct.unl.pt.instagramplus.Services.Result.error;
import static fct.unl.pt.instagramplus.Services.Result.ok;

@Service
public class MessageServiceClass implements MessageServiceInterface{

    @Autowired
    MessagesRepository messagesRepository;

    @Autowired
    AccountsRepository accountsRepository;


    @Override
    public Result<Long> sendMessage(Message message) {

        Long fromAccountId = message.getFromUserId();
        Long toAccountId = message.getToUserId();

        if(!accountExists(fromAccountId) || !accountExists(toAccountId))
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
    public Result<List<Message>> getAllMessagesFromConversation(Long accountId, Long toAccountId) {
        if(!accountExists(accountId) || !accountExists(toAccountId))
            return error(NOT_FOUND);
        List<Message> list = messagesRepository.getAllByFromUserIdAndToUserId(accountId, toAccountId);
        List<Message> list2 = messagesRepository.getAllByFromUserIdAndToUserId(toAccountId, accountId);

        for (Message mes : list2) {
            mes.setReceivedDate();
            messagesRepository.save(mes);
        }
        list.addAll(list2);
        Collections.sort(list, (m1, m2) -> {
            SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DATE_PATTERN);
            try {
                Date date1 = sdf.parse(m1.getSendedDate());
                Date date2 = sdf.parse(m2.getSendedDate());
                return date1.compareTo(date2);
            } catch (ParseException e) {
                return 0;
            }
        });
        return ok(list);
    }

    private boolean accountExists(Long id){
        return accountsRepository.getAccountById(id) != null;
    }
}

