package fct.unl.pt.instagramplus.Services.PublicationsServices;

import fct.unl.pt.instagramplus.Controllers.Publications.PublicationControllerinterface;
import fct.unl.pt.instagramplus.Models.Comment;
import fct.unl.pt.instagramplus.Models.Messages.Message;
import fct.unl.pt.instagramplus.Models.Publications.Publication;
import fct.unl.pt.instagramplus.Models.Reactions.Reaction;
import fct.unl.pt.instagramplus.Repositories.CommentsRepository;
import fct.unl.pt.instagramplus.Repositories.Publications.PublicationsRepository;
import fct.unl.pt.instagramplus.Repositories.ReactionsRepository;
import fct.unl.pt.instagramplus.Services.Result;
import org.springframework.http.ResponseEntity;
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
      List<Publication> pub=publicationRepository.getAllByFromUserId(id);
        if(pub.isEmpty())
            return error(NOT_FOUND);
        return ok(pub);
    }

    @Override
    public Result<Long> addCommnet(Comment comment) {
        Comment com=commentRepository.save(comment);
        return ok(com.getId());
    }

    @Override
    public Result<Void> deleteComment(Long idUser,Long idPub) {
        List<Comment> comm=commentRepository.getAllByPublicationId(idPub);
        if(comm.isEmpty())
            return error(NOT_FOUND);
        for (Comment com : comm) {
            if(com.getUserId()==idUser) {
                commentRepository.delete(com);
                break;
            }
        }
        return ok();
    }




    @Override
    public Result<Long> addLike(Reaction reaction) {
        Reaction react=reactionsRepository.save(reaction);
        return ok(react.getId());
    }

    @Override
    public Result<Void> deleteLike(Long idUser,Long idPub) {
       List <Reaction> reacts=reactionsRepository.getAllByPublicationId(idPub);
        if(reacts.isEmpty())
            return error(NOT_FOUND);
        for (Reaction react : reacts) {
            if(react.getUserId()==idUser) {
                reactionsRepository.delete(react);
                break;
            }
        }

        return ok();
    }

    @Override
    public Result<List<Comment>> getAllComments(Long id) {
        List<Comment> com=commentRepository.getAllByPublicationId(id);
        if(com.isEmpty())
            return error(NOT_FOUND);
        return ok(com);
    }

    @Override
    public Result<List<Reaction>> getAllLikes(Long id) {
        List<Reaction> react=reactionsRepository.getAllByPublicationId(id);
        if(react.isEmpty())
            return error(NOT_FOUND);
        return ok(react);
    }
}
