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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.util.List;

@RestController
public class PublicationsControllerClass implements PublicationsControllerInterface {

    @Autowired
    PublicationsServices publicationsServices;

    @Autowired
    AccountsRepository accountsRepository;

    @Autowired
    PublicationsRepository publicationRepository;

    @Autowired
    CommentsRepository commentRepository;

    @Autowired
    ReactionsRepository reactionsRepository;

    @Autowired
    FollowersRepository followersRepository;

    @Value("${userAuth}")
    private boolean useAuth;

    @Override
    public ResponseEntity<Long> createPublication(Long accountRequestId, Publication publication) {
        Logger.info("Request: CREATE PUBLICATION");
        if (useAuth && !accountRequestId.equals(publication.getOwnerId()))
            return ResponseEntity.status(UNAUTHORIZED).build();
        return Response.resultOrErrorCode(publicationsServices.createpublication(publication));
    }

    @Override
    public ResponseEntity<Void> deletePublication(Long accountRequestId, Long id) {
        Logger.info("Request: DELETE PUBLICATION");
        Publication pub = publicationRepository.getPublicationById(id);
        if (useAuth && !accountRequestId.equals(pub.getOwnerId()))
            return ResponseEntity.status(UNAUTHORIZED).build();
        return Response.resultOrErrorCode(publicationsServices.deletePublication(id));
    }

    @Override
    public ResponseEntity<Publication> editPublication(Long accountRequestId, String description, Long id) {
        Logger.info("Request: EDIT PUBLICATION");
        Publication pub = publicationRepository.getPublicationById(id);
        if (useAuth && !accountRequestId.equals(pub.getOwnerId()))
            return ResponseEntity.status(UNAUTHORIZED).build();
        return Response.resultOrErrorCode(publicationsServices.editPublication(description, id));
    }

    @Override
    public ResponseEntity<List<Publication>> getAllPublications(Long accountRequestId, Long id) {
        Logger.info("Request: ALL PUBLICATIONS");
        if (useAuth && !accountRequestId.equals(id)) {
            Follower fll = followersRepository.getByAccountIdAndIsFollowingId(accountRequestId, id);
            if (fll == null)
                return ResponseEntity.status(UNAUTHORIZED).build();
        }
        return Response.resultOrErrorCode(publicationsServices.getAllPublications(id));
    }

    @Override
    public ResponseEntity<Long> AddComment(Long accountRequestId, Comment comment) {
        Logger.info("Request: ADD COMMENTS");
        if (!accountRequestId.equals(comment.getUserId()))
            return ResponseEntity.status(UNAUTHORIZED).build();
        return Response.resultOrErrorCode(publicationsServices.addCommnet(comment));
    }

    @Override
    public ResponseEntity<Long> AddLike(Long accountRequestId, Reaction like) {
        Logger.info("Request: ADD LIKE");
        if (useAuth && !accountRequestId.equals(like.getUserId()))
            return ResponseEntity.status(UNAUTHORIZED).build();
        return Response.resultOrErrorCode(publicationsServices.addLike(like));
    }

    @Override
    public ResponseEntity<Void> deleteComment(Long accountRequestId, Long idComment) {
        Logger.info("Request: DETELE COMMENT");
        Comment com = commentRepository.getCommentById(idComment);
        if (useAuth && !accountRequestId.equals(com.getUserId()))
            return ResponseEntity.status(UNAUTHORIZED).build();
        return Response.resultOrErrorCode(publicationsServices.deleteComment(idComment));
    }

    @Override
    public ResponseEntity<Void> deleteLike(Long accountRequestId, Long idLike) {
        Logger.info("Request: DELETE LIKE");
        Reaction react = reactionsRepository.getReactionById(idLike);
        if (useAuth && !accountRequestId.equals(react.getUserId()))
            return ResponseEntity.status(UNAUTHORIZED).build();
        return Response.resultOrErrorCode(publicationsServices.deleteLike(idLike));
    }

    @Override
    public ResponseEntity<List<Comment>> getAllComments(Long accountRequestId, Long id) {
        Logger.info("Request: ALL COMMENTS");
        return Response.resultOrErrorCode(publicationsServices.getAllComments(id));
    }

    @Override
    public ResponseEntity<List<Reaction>> getAllLikes(Long accountRequestId, Long id) {
        Logger.info("Request: ALL LIKES");
        return Response.resultOrErrorCode(publicationsServices.getAllLikes(id));
    }
}
