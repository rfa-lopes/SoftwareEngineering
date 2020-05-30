package fct.unl.pt.instagramplus.Services.Stories;

import fct.unl.pt.instagramplus.Models.Publications.Publication;
import fct.unl.pt.instagramplus.Services.Result;

import java.util.List;

public interface StoriesServiceInterface {
    Result<Long> createStory(Publication publication);
    Result<Void>deleteStory(Long id);
    Result<Publication> getStory(Long id);
    Result<List<Publication>> getAllStories(Long id);
}
