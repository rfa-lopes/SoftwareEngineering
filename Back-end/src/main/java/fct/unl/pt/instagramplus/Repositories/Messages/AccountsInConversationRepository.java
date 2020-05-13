package fct.unl.pt.instagramplus.Repositories.Messages;

import fct.unl.pt.instagramplus.Models.Messages.AccountInConversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountsInConversationRepository extends JpaRepository<AccountInConversation, Long> {

    AccountInConversation getFirstById(Long id);

    List<AccountInConversation> getAllByConversationId(Long conversationId); //Todos os utilizadores numa conversa

    List<AccountInConversation> getAllByAccountId(Long accountId); //Todas as conversas de um utilziador


}
