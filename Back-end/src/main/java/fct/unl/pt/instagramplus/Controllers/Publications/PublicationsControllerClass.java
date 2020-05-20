package fct.unl.pt.instagramplus.Controllers.Publications;

import fct.unl.pt.instagramplus.Controllers.Response;
import fct.unl.pt.instagramplus.Models.Account;
import fct.unl.pt.instagramplus.Models.Comment;
import fct.unl.pt.instagramplus.Models.Follower;
import fct.unl.pt.instagramplus.Models.Publications.Publication;
import fct.unl.pt.instagramplus.Models.Reactions.Reaction;
import fct.unl.pt.instagramplus.Repositories.Accounts.AccountsRepository;
import fct.unl.pt.instagramplus.Repositories.Accounts.FollowersRepository;
import fct.unl.pt.instagramplus.Repositories.CommentsRepository;
import fct.unl.pt.instagramplus.Repositories.Publications.PublicationsRepository;
import fct.unl.pt.instagramplus.Repositories.ReactionsRepository;
import fct.unl.pt.instagramplus.Services.PublicationsServices.PublicationsServices;
import fct.unl.pt.instagramplus.Utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.util.List;

public class PublicationsControllerClass implements PublicationsControllerInterface {

    @Autowired
    PublicationsServices publi;

    @Autowired
    private AccountsRepository accountsRepository;
    @Autowired
    PublicationsRepository publicationRepository;

    @Autowired
    CommentsRepository commentRepository;

    @Autowired
    ReactionsRepository reactionsRepository;
    @Autowired
    private FollowersRepository followersRepository;

    @Override
    public ResponseEntity<Long> createPublication(Long accountRequestId,Publication publication) {
        Logger.info("Request: CREATE PUBLICATION");
        if(accountRequestId!=publication.getOwnerId()) {
        	 return ResponseEntity.status(UNAUTHORIZED).build();
        }
        return Response.resultOrErrorCode(publi.createpublication(publication));
    }

    @Override
    public ResponseEntity<Void> deletePublication(Long accountRequestId,Long id) {
        Logger.info("Request: DELETE PUBLICATION");
       Publication pub=publicationRepository.getPublicationById(id);
       if(accountRequestId!=pub.getOwnerId())
           return ResponseEntity.status(UNAUTHORIZED).build();
        return Response.resultOrErrorCode(publi.deletePublication(id));
    }

    @Override
    public ResponseEntity<Publication> editPublication(Long accountRequestId,String description, Long id) {
        Logger.info("Request: EDIT PUBLICATION");
        Publication pub=publicationRepository.getPublicationById(id);
        if(accountRequestId!=pub.getOwnerId())
            return ResponseEntity.status(UNAUTHORIZED).build();
        return Response.resultOrErrorCode(publi.editPublication(description,id));
    }

    @Override
    public ResponseEntity<List<Publication>> getAllPublications(Long accountRequestId,Long id) {
        if (accountRequestId!=id){
            Follower fll=followersRepository.getByAccountIdAndIsFollowingId(accountRequestId,id);
            if(fll==null)
                return ResponseEntity.status(UNAUTHORIZED).build();
        }
        Logger.info("Request: ALL PUBLICATIONS");
        return Response.resultOrErrorCode(publi.getAllPublications(id));
    }

    @Override
    public ResponseEntity<Long> AddComment(Long accountRequestId,Comment comment) {
        Logger.info("Request: ADD COMMENTS");
        if(accountRequestId!=comment.getUserId())
       	 return ResponseEntity.status(UNAUTHORIZED).build();
        return Response.resultOrErrorCode(publi.addCommnet(comment));
    }

    @Override
    public ResponseEntity<Long> AddLike(Long accountRequestId,Reaction like) {
        Logger.info("Request: ADD LIKE");
        if(accountRequestId!=like.getUserId())
            return ResponseEntity.status(UNAUTHORIZED).build();

        return Response.resultOrErrorCode(publi.addLike(like));
    }

    @Override
    public ResponseEntity<Void> deleteComment(Long accountRequestId,Long idComment) {
        Logger.info("Request: DETELE COMMENT");
        Comment com=commentRepository.getCommentById(idComment);
        if(accountRequestId!=com.getUserId())
            return ResponseEntity.status(UNAUTHORIZED).build();
        return Response.resultOrErrorCode(publi.deleteComment(idComment));
    }

    @Override
    public ResponseEntity<Void> deleteLike(Long accountRequestId,Long idLike) {
        Logger.info("Request: DELETE LIKE");
        Reaction react=reactionsRepository.getReactionById(idLike);
        if(accountRequestId!=react.getUserId())
            return ResponseEntity.status(UNAUTHORIZED).build();
        return Response.resultOrErrorCode(publi.deleteLike(idLike));
    }

    @Override
    public ResponseEntity<List<Comment>> getAllComments(Long accountRequestId,Long id) {
        Logger.info("Request: ALL COMMENTS");
        return Response.resultOrErrorCode(publi.getAllComments(id));
    }

    @Override
    public ResponseEntity<List<Reaction>> getAllLikes(Long accountRequestId,Long id) {
        Logger.info("Request: ALL LIKES");
        return Response.resultOrErrorCode(publi.getAllLikes(id));
    }
}
