package fct.unl.pt.instagramplus.Repositories;

import fct.unl.pt.instagramplus.Models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comment, Long> {

    Comment getCommentById(Long id);

    List<Comment> getAllByPublicationId(Long publicationId);
}
