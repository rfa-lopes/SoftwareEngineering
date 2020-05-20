package fct.unl.pt.instagramplus.Controllers.Publications;

import fct.unl.pt.instagramplus.Controllers.Response;
import fct.unl.pt.instagramplus.Models.Comment;
import fct.unl.pt.instagramplus.Models.Publications.Publication;
import fct.unl.pt.instagramplus.Models.Reactions.Reaction;
import fct.unl.pt.instagramplus.Services.PublicationsServices.PublicationsServices;
import fct.unl.pt.instagramplus.Utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class PublicationsControllerClass implements PublicationsControllerInterface {

    @Autowired
    PublicationsServices publi;
    @Override
    public ResponseEntity<Long> createPublication(Publication publication) {
        Logger.info("Request: CREATE PUBLICATION");
        return Response.resultOrErrorCode(publi.createpublication(publication));
    }

    @Override
    public ResponseEntity<Void> deletePublication(Long id) {
        Logger.info("Request: DELETE PUBLICATION");
        return Response.resultOrErrorCode(publi.deletePublication(id));
    }

    @Override
    public ResponseEntity<Publication> editPublication(String description, Long id) {
        Logger.info("Request: EDIT PUBLICATION");
        return Response.resultOrErrorCode(publi.editPublication(description,id));
    }

    @Override
    public ResponseEntity<List<Publication>> getAllPublications(Long id) {
        Logger.info("Request: ALL PUBLICATIONS");
        return Response.resultOrErrorCode(publi.getAllPublications(id));
    }

    @Override
    public ResponseEntity<Long> AddComment(Comment comment) {
        Logger.info("Request: ADD COMMENTS");
        return Response.resultOrErrorCode(publi.addCommnet(comment));
    }

    @Override
    public ResponseEntity<Long> AddLike(Reaction like) {
        Logger.info("Request: ADD LIKE");
        return Response.resultOrErrorCode(publi.addLike(like));
    }

    @Override
    public ResponseEntity<Void> deleteComment(Long idLike) {
        Logger.info("Request: DETELE COMMENT");
        return Response.resultOrErrorCode(publi.deleteComment(idLike));
    }

    @Override
    public ResponseEntity<Void> deleteLike(Long idLike) {
        Logger.info("Request: DELETE LIKE");
        return Response.resultOrErrorCode(publi.deleteLike(idLike));
    }

    @Override
    public ResponseEntity<List<Comment>> getAllComments(Long id) {
        Logger.info("Request: ALL COMMENTS");
        return Response.resultOrErrorCode(publi.getAllComments(id));
    }

    @Override
    public ResponseEntity<List<Reaction>> getAllLikes(Long id) {
        Logger.info("Request: ALL LIKES");
        return Response.resultOrErrorCode(publi.getAllLikes(id));
    }
}
