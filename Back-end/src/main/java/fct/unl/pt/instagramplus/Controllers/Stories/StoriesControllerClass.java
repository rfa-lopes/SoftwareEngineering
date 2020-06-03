package fct.unl.pt.instagramplus.Controllers.Stories;

import fct.unl.pt.instagramplus.Controllers.Response;
import fct.unl.pt.instagramplus.Models.Publications.Publication;
import fct.unl.pt.instagramplus.Models.Stories;
import fct.unl.pt.instagramplus.Repositories.StoriesRepository;
import fct.unl.pt.instagramplus.Services.Stories.StoriesService;
import fct.unl.pt.instagramplus.Utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StoriesControllerClass implements StoriesControllerInterface {

    @Autowired
    StoriesService storiesService;
    @Autowired
    StoriesRepository storiesRepository;

    @Value("${userAuth}")
    private boolean useAuth;

    @Override
    public ResponseEntity<Long> createStory(Long accountRequestId, Stories publication) {
        Logger.info("Request: CREATE STORY");
        return Response.resultOrErrorCode(storiesService.createStory(publication));
    }

    @Override
    public ResponseEntity<Void> deleteStory(Long accountRequestId, Long id) {
        Logger.info("Request: DELETE Story");
        return Response.resultOrErrorCode(storiesService.deleteStory(id));
    }

    @Override
    public ResponseEntity<List<Stories>> getAllStories(Long accountRequestId, Long id) {
        Logger.info("Request: GET ALL STORIES");
        return Response.resultOrErrorCode(storiesService.getAllStories(id));
    }

    @Override
    public ResponseEntity<Stories> getStory(Long accountRequestId, Long id) {
        Logger.info("Request: GET STORIES");
        return Response.resultOrErrorCode(storiesService.getStory(id));
    }
}
