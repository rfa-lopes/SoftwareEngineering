package fct.unl.pt.instagramplus.Services.PublicationsServices;

import fct.unl.pt.instagramplus.Models.Account;
import fct.unl.pt.instagramplus.Models.Comment;
import fct.unl.pt.instagramplus.Models.Publications.Publication;
import fct.unl.pt.instagramplus.Models.Reactions.Reaction;
import fct.unl.pt.instagramplus.Repositories.Accounts.AccountsRepository;
import fct.unl.pt.instagramplus.Repositories.CommentsRepository;
import fct.unl.pt.instagramplus.Repositories.Publications.PublicationsRepository;
import fct.unl.pt.instagramplus.Repositories.ReactionsRepository;
import fct.unl.pt.instagramplus.Services.Result;
import fct.unl.pt.instagramplus.Utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static fct.unl.pt.instagramplus.Services.Result.ErrorCode.*;
import static fct.unl.pt.instagramplus.Services.Result.error;
import static fct.unl.pt.instagramplus.Services.Result.ok;

@Service
public class PublicationsServices implements PublicationsServiceInterface {

    @Autowired
    PublicationsRepository publicationRepository;

    @Autowired
    CommentsRepository commentRepository;

    @Autowired
    ReactionsRepository reactionsRepository;

    @Autowired
    private AccountsRepository accountsRepository;

    @Override
    public Result<Long> createpublication(Publication publication) {
        if(publication.getImage() == null)
            error(BAD_REQUEST);
        publication.setPublicationDate(DateUtil.getAtualDate());
        Publication pub =publicationRepository.save(publication);
        return ok(pub.getId());
    }

    @Override
    public Result<Void> deletePublication(Long id) {
        Publication pub=publicationRepository.getPublicationById(id);
        if(pub == null)
            return error(NOT_FOUND);
        //Apagar todos os comentarios da publicacao
        commentRepository.deleteAllByPublicationId(id);
        //Apagar todas as reacoes da publicacao
        reactionsRepository.deleteAllByPublicationId(id);
        publicationRepository.deleteById(id);
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
        Account account=accountsRepository.getAccountById(id);
        if(account==null)
            return error(NOT_FOUND);
        List<Publication> pub=publicationRepository.getAllPublicationsByOwnerId(id);
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
        commentRepository.deleteById(idLike);
        return ok();
    }

    @Override
    public Result<Long> addLike(Reaction reaction) {
        Publication pub= publicationRepository.getPublicationById(reaction.getPublicationId());
        if (pub==null)
            return error(NOT_FOUND);
        Reaction re=reactionsRepository.getReactionByUserIdAndPublicationId(reaction.getUserId(),reaction.getPublicationId());
        if(re !=null){
            return error(CONFLICT);
        }
        reaction.setReactionDate(DateUtil.getAtualDate());
        Reaction react=reactionsRepository.save(reaction);
        return ok(react.getId());
    }

    @Override
    public Result<Void> deleteLike(Long idLike) {
        reactionsRepository.deleteById(idLike);
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
