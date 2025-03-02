package fct.unl.pt.instagramplus.Repositories;

import fct.unl.pt.instagramplus.Models.Reactions.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReactionsRepository extends JpaRepository<Reaction, Long> {

    Reaction getReactionById(Long id);

    List<Reaction> getAllByPublicationId(Long publicationId);

    void deleteAllByPublicationId(Long publicationId);

    void deleteAllByUserId(Long AccountId);
    Reaction getReactionByUserIdAndPublicationId(Long AccountId,Long PublicationId);


}
