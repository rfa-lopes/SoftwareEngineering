package fct.unl.pt.instagramplus.Services.Stories;

import fct.unl.pt.instagramplus.Models.Publications.Publication;
import fct.unl.pt.instagramplus.Models.Stories;
import fct.unl.pt.instagramplus.Services.Result;

import java.util.List;

public interface StoriesServiceInterface {
    Result<Long> createStory(Stories publication);
    Result<Void>deleteStory(Long id);
    Result<Stories> getStory(Long id);
    Result<List<Stories>> getAllStories(Long id);
}
