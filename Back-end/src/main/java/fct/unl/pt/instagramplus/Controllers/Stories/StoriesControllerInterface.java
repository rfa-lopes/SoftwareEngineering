package fct.unl.pt.instagramplus.Controllers.Stories;

import fct.unl.pt.instagramplus.Models.Publications.Publication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = StoriesControllerInterface.BASE_URL)
public interface StoriesControllerInterface {
    String BASE_URL = "/stories";
    String ADD = "/poststory";
    String DELETE = "/deletestory/{id}";
    String GET= "/get/{id}";
    String GET_ALL_Stories_FROM_PROFILE = "/allstories/{id}";

    @PostMapping(
            value = ADD,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Long> createStory(
            @RequestAttribute("id") Long accountRequestId,
            @RequestBody Publication publication);
    @DeleteMapping(
            value = DELETE,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> deleteStory(
            @RequestAttribute("id") Long accountRequestId,
            @PathVariable( "id" ) Long id);
    @GetMapping(
            value = GET_ALL_Stories_FROM_PROFILE,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<Publication>> getAllStories(@RequestAttribute("id") Long accountRequestId,
                                                         @PathVariable( "id" ) Long id);

    @GetMapping(
            value = GET,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Publication> getStory(@RequestAttribute("id") Long accountRequestId,
                                                    @PathVariable( "id" ) Long id);
}

