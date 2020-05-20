package fct.unl.pt.instagramplus.Services.PublicationsServices;

import fct.unl.pt.instagramplus.Models.Comment;
import fct.unl.pt.instagramplus.Models.Publications.Publication;
import fct.unl.pt.instagramplus.Models.Reactions.Reaction;
import fct.unl.pt.instagramplus.Services.Result;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PublicationsServiceInterface {
    Result<Long> createpublication(Publication publication);
    Result<Void>deletePublication(Long id);
    Result<Publication> editPublication(String description,Long id);
    Result<List<Publication>> getAllPublications(Long id);
    Result <Long> addCommnet(Comment comment);
    Result<Void> deleteComment(Long idLike);
    //Result <Comment>editComment(Long id,String description);
    Result<Long>addLike(Reaction reaction);
    Result<Void>deleteLike(Long idLike);
    Result<List<Comment>> getAllComments(Long id);
    Result<List<Reaction>> getAllLikes(Long id);


    
}
