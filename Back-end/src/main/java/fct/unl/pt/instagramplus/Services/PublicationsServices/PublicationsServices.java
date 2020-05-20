package fct.unl.pt.instagramplus.Services.PublicationsServices;

import fct.unl.pt.instagramplus.Models.Comment;
import fct.unl.pt.instagramplus.Models.Publications.Publication;
import fct.unl.pt.instagramplus.Models.Reactions.Reaction;
import fct.unl.pt.instagramplus.Repositories.CommentsRepository;
import fct.unl.pt.instagramplus.Repositories.Publications.PublicationsRepository;
import fct.unl.pt.instagramplus.Repositories.ReactionsRepository;
import fct.unl.pt.instagramplus.Services.Result;
import org.springframework.stereotype.Service;

import java.util.List;

import static fct.unl.pt.instagramplus.Services.Result.ErrorCode.NOT_FOUND;
import static fct.unl.pt.instagramplus.Services.Result.error;
import static fct.unl.pt.instagramplus.Services.Result.ok;

@Service
public class PublicationsServices implements PublicationsServiceInterface {

    PublicationsRepository publicationRepository;

    CommentsRepository commentRepository;

    ReactionsRepository reactionsRepository;

    @Override
    public Result<Long> createpublication(Publication publication) {
        Publication pub =publicationRepository.save(publication);
        return ok(pub.getId());
    }

    @Override
    public Result<Void> deletePublication(Long id) {
        Publication pub=publicationRepository.getPublicationById(id);
        //TODO
        if(pub == null)
            return error(NOT_FOUND);
        publicationRepository.delete(pub);
        return ok();
    }

    @Override
    public Result<Publication> editPublication(String description, Long id) {
        Publication pub=publicationRepository.getPublicationById(id);
        if(pub == null)
            return error(NOT_FOUND);
        pub.setDescription(description);
        Publication p=publicationRepository.save(pub);
        return ok(p);
    }

    @Override
    public Result<List<Publication>> getAllPublications(Long id) {
      List<Publication> pub=publicationRepository.getAllPublicationsById(id);
        if(pub==null)
            return error(NOT_FOUND);
        return ok(pub);
    }

    @Override
    public Result<Long> addCommnet(Comment comment) {
        Comment com=commentRepository.save(comment);
        return ok(com.getId());
    }

    @Override
    public Result<Void> deleteComment(Long idLike) {
    	Comment com=commentRepository.getCommentById(idLike);
        Comment comm=commentRepository.delete(com);;


        return ok();
    }

    @Override
    public Result<Long> addLike(Reaction reaction) {
        Reaction react=reactionsRepository.save(reaction);
        return ok(react.getId());
    }

    @Override
    public Result<Void> deleteLike(Long idLike) {
    	Reaction react=reactionsRepository.getReactionById(idLike);
        Reaction reacts=reactionsRepository.delete(react);


        return ok();
    }

    @Override
    public Result<List<Comment>> getAllComments(Long id) {
        List<Comment> com=commentRepository.getAllByPublicationId(id);
        if(com==null)
            return error(NOT_FOUND);
        return ok(com);
    }

    @Override
    public Result<List<Reaction>> getAllLikes(Long id) {
        List<Reaction> react=reactionsRepository.getAllByPublicationId(id);
        if(react==null)
            return error(NOT_FOUND);
        return ok(react);
    }
}
