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
    String ADDCOMENT = "/postcomment/{idLike}";
    String ADDLIKE = "/postlike";
    String DELETECOMMENT = "/deletecomment/{id}";
    String DELETELIKE = "/deletelike/{idLike}";
    String GET_ALL_COMMENTS_FROM_PUBLICATION = "/allComments/{id}";
    String GET_ALL_Likes_FROM_PUBLICATION = "/allComments/{id}";

    @PostMapping(
            value = ADD,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Long> createPublication(@RequestAttribute("id") Long accountRequestId,
                    @RequestBody Publication publication);
    @PostMapping(
            value = DELETE)
    ResponseEntity<Void> deletePublication(   @RequestAttribute("id") Long accountRequestId,
                    @PathVariable( "id" ) Long id);

    @PostMapping(
            value = EDIT,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Publication> editPublication(@RequestAttribute("id") Long accountRequestId,
            @RequestBody String description, @PathVariable( "id" ) Long id);

    @GetMapping(
            value = GET_ALL_PUBLICATIONS_FROM_PROFILE,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<Publication>> getAllPublications(@RequestAttribute("id") Long accountRequestId,
            @PathVariable( "id" ) Long id);

    @PostMapping(
            value = ADDCOMENT,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Long> AddComment(@RequestAttribute("id") Long accountRequestId,
            @RequestBody Comment comment);

    @PostMapping(
            value = ADDLIKE,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Long> AddLike(@RequestAttribute("id") Long accountRequestId,
            @RequestBody Reaction like);

    @PostMapping(
            value = DELETECOMMENT)
    ResponseEntity<Void> deleteComment(@RequestAttribute("id") Long accountRequestId,
            @PathVariable( "idLike" )Long idLike);

    @PostMapping(
            value = DELETELIKE)
    ResponseEntity<Void> deleteLike( @RequestAttribute("id") Long accountRequestId,  
    		@PathVariable( "idLike" ) Long idLike);

    @GetMapping(
            value = GET_ALL_COMMENTS_FROM_PUBLICATION,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<Comment>> getAllComments(@RequestAttribute("id") Long accountRequestId,
            @PathVariable( "id" ) Long id);

    @GetMapping(
            value = GET_ALL_Likes_FROM_PUBLICATION,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<Reaction>> getAllLikes(@RequestAttribute("id") Long accountRequestId,
            @PathVariable( "id" ) Long id);

}
