package fct.unl.pt.instagramplus.Controllers.Publications;

import fct.unl.pt.instagramplus.Models.Comment;
import fct.unl.pt.instagramplus.Models.Publications.Publication;
import fct.unl.pt.instagramplus.Models.Reactions.Reaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = PublicationsControllerInterface.BASE_URL)
public interface PublicationsControllerInterface {
    
    String BASE_URL = "/publications";

    String ADD = "/postpublication";
    String DELETE = "/detetepublication/{id}";
    String GET_ALL_PUBLICATIONS_FROM_PROFILE = "/allpublications/{id}";
    String EDIT = "/editpublication/{id}";
    String ADDCOMENT = "/postcomment/{iduser}/{idpub}";
    String ADDLIKE = "/postlike";
    String DELETECOMMENT = "/deletecomment/{id}";
    String DELETELIKE = "/deletelike/{iduser}/{idpub}";
    String GET_ALL_COMMENTS_FROM_PUBLICATION = "/allComments/{id}";
    String GET_ALL_Likes_FROM_PUBLICATION = "/allComments/{id}";

    @PostMapping(
            value = ADD,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Long> createPublication(
                    @RequestBody Publication publication);
    @PostMapping(
            value = DELETE)
    ResponseEntity<Void> deletePublication(
                    @PathVariable( "id" ) Long id);

    @PostMapping(
            value = EDIT,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Publication> editPublication(
            @RequestBody String description, @PathVariable( "id" ) Long id);

    @GetMapping(
            value = GET_ALL_PUBLICATIONS_FROM_PROFILE,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<Publication>> getAllPublications(
            @PathVariable( "id" ) Long id);

    @PostMapping(
            value = ADDCOMENT,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Long> AddComment(
            @RequestBody Comment comment);

    @PostMapping(
            value = ADDLIKE,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Long> AddLike(
            @RequestBody Reaction like);

    @PostMapping(
            value = DELETECOMMENT)
    ResponseEntity<Void> deleteComment(
            @PathVariable( "iduser" ) Long idUser, @PathVariable( "idpub" ) Long idPub);

    @PostMapping(
            value = DELETELIKE)
    ResponseEntity<Void> deleteLike(
            @PathVariable( "iduser" ) Long idUser, @PathVariable( "idpub" ) Long idPub);

    @GetMapping(
            value = GET_ALL_COMMENTS_FROM_PUBLICATION,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<Comment>> getAllComments(
            @PathVariable( "id" ) Long id);

    @GetMapping(
            value = GET_ALL_Likes_FROM_PUBLICATION,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<Reaction>> getAllLikes(
            @PathVariable( "id" ) Long id);

}
