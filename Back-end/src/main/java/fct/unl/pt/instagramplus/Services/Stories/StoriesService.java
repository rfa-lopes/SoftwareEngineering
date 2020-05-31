package fct.unl.pt.instagramplus.Services.Stories;

import fct.unl.pt.instagramplus.Models.Account;
import fct.unl.pt.instagramplus.Models.Publications.Publication;
import fct.unl.pt.instagramplus.Models.Stories;
import fct.unl.pt.instagramplus.Repositories.Accounts.AccountsRepository;
import fct.unl.pt.instagramplus.Repositories.StoriesRepository;
import fct.unl.pt.instagramplus.Services.Result;
import fct.unl.pt.instagramplus.Utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static fct.unl.pt.instagramplus.Services.Result.ErrorCode.NOT_FOUND;
import static fct.unl.pt.instagramplus.Services.Result.error;
import static fct.unl.pt.instagramplus.Services.Result.ok;

@Service
public class StoriesService implements StoriesServiceInterface {
    @Autowired
    StoriesRepository storiesRepository;
    @Autowired
    private AccountsRepository accountsRepository;

    @Override
    public Result<Long> createStory(Stories publication) {
        publication.setPublicationDate(DateUtil.getAtualDate());
        publication.setExpireDate(DateUtil.addHoursToDate(DateUtil.getAtualDate()));
        Stories pub = storiesRepository.save(publication);
        return ok(pub.getId());
    }

    @Override
    public Result<Void> deleteStory(Long id) {
        Stories pub = storiesRepository.getStoriesById(id);
        if (pub == null)
            return error(NOT_FOUND);
        storiesRepository.deleteById(id);
        return ok();
    }

    @Override
    public Result<Stories> getStory(Long id) {

        Stories pub = storiesRepository.getStoriesById(id);
        if (pub == null)
            return error(NOT_FOUND);

        if (pub.isExpired()) {
            storiesRepository.delete(pub);
            return error(NOT_FOUND);
        }

        return ok(pub);
    }

    @Override
    public Result<List<Stories>> getAllStories(Long id) {
        Account account = accountsRepository.getAccountById(id);
        if (account == null)
            return error(NOT_FOUND);
        List<Stories> pubs = storiesRepository.getAllByOwnerId(id);
        for (Stories s : pubs)
            if (s.isExpired()) {
                storiesRepository.delete(s);
                pubs.remove(s);
            }
        return ok(pubs);

    }
}
