package fct.unl.pt.instagramplus.Repositories;

import fct.unl.pt.instagramplus.Models.Messages.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversationsRepository extends JpaRepository<Conversation, Long> {

    Conversation getConversationBy(Long id);

}
