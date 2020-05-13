package fct.unl.pt.instagramplus.Repositories.Messages;

import fct.unl.pt.instagramplus.Models.Messages.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessagesRepository extends JpaRepository<Message, Long> {

    Message getMessagenById(Long id);

    List<Message> getAllByFromUserIdAndToUserId(Long fromUserId, Long toUserId);


}
