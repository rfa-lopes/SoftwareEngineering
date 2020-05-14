package fct.unl.pt.instagramplus.Controllers.Publications;

import fct.unl.pt.instagramplus.Controllers.Response;
import fct.unl.pt.instagramplus.Models.Comment;
import fct.unl.pt.instagramplus.Models.Publications.Publication;
import fct.unl.pt.instagramplus.Models.Reactions.Reaction;
import fct.unl.pt.instagramplus.Services.PublicationsServices.PublicationsServices;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class PublicationsControllerClass implements PublicationControllerinterface {
    PublicationsServices publi;
    @Override
    public ResponseEntity<Long> createPublication(Publication publication) {
        return Response.resultOrErrorCode(publi.createpublication(publication));
    }

    @Override
    public ResponseEntity<Void> deletePublication(Long id) {
        return Response.resultOrErrorCode(publi.deletePublication(id));
    }

    @Override
    public ResponseEntity<Publication> editPublication(String description, Long id) {
        return Response.resultOrErrorCode(publi.editPublication(description,id));
    }

    @Override
    public ResponseEntity<List<Publication>> getAllPublications(Long id) {
        return Response.resultOrErrorCode(publi.getAllPublications(id));
    }

    @Override
    public ResponseEntity<Long> AddComment(Comment comment) {
        return Response.resultOrErrorCode(publi.addCommnet(comment));
    }

    @Override
    public ResponseEntity<Long> AddLike(Reaction like) {
        return Response.resultOrErrorCode(publi.addLike(like));
    }

    @Override
    public ResponseEntity<Void> deleteComment(Long idUser, Long idPub) {
        return Response.resultOrErrorCode(publi.deleteComment(idUser,idPub));
    }

    @Override
    public ResponseEntity<Void> deleteLike(Long idUser, Long idPub) {
        return Response.resultOrErrorCode(publi.deleteLike(idUser,idPub));
    }

    @Override
    public ResponseEntity<List<Comment>> getAllComments(Long id) {
        return Response.resultOrErrorCode(publi.getAllComments(id));
    }

    @Override
    public ResponseEntity<List<Reaction>> getAllLikes(Long id) {
        return Response.resultOrErrorCode(publi.getAllLikes(id));
    }
}
