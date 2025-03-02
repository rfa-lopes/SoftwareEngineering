package fct.unl.pt.instagramplus.Services.AccountsServices;

import fct.unl.pt.instagramplus.Models.Account;
import fct.unl.pt.instagramplus.Models.Follower;
import fct.unl.pt.instagramplus.Models.ProfileViewer;
import fct.unl.pt.instagramplus.Models.Publications.Publication;
import fct.unl.pt.instagramplus.Models.Stories;
import fct.unl.pt.instagramplus.Repositories.Accounts.AccountsRepository;
import fct.unl.pt.instagramplus.Repositories.Accounts.FollowersRepository;
import fct.unl.pt.instagramplus.Repositories.Accounts.ProfileViewersRepository;
import fct.unl.pt.instagramplus.Repositories.CommentsRepository;
import fct.unl.pt.instagramplus.Repositories.Publications.PublicationsRepository;
import fct.unl.pt.instagramplus.Repositories.ReactionsRepository;
import fct.unl.pt.instagramplus.Repositories.StoriesRepository;
import fct.unl.pt.instagramplus.Services.Result;
import fct.unl.pt.instagramplus.Utils.DateUtil;
import fct.unl.pt.instagramplus.Utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static fct.unl.pt.instagramplus.Services.Result.ErrorCode.*;
import static fct.unl.pt.instagramplus.Services.Result.error;
import static fct.unl.pt.instagramplus.Services.Result.ok;

@Service
public class AccountServiceClass implements AccountServiceInterface {

    @Autowired
    AccountsRepository accountsRepository;

    @Autowired
    ProfileViewersRepository profileViewersRepository;

    @Autowired
    FollowersRepository followersRepository;

    @Autowired
    PublicationsRepository publicationRepository;

    @Autowired
    CommentsRepository commentRepository;

    @Autowired
    ReactionsRepository reactionsRepository;

    @Autowired
    StoriesRepository storiesRepository;

    @Override
    public Result<Long> createAccount(Account account) {
        if (accountsRepository.existsAccountByEmailOrUsername(account.getEmail(), account.getUsername()))
            return error(CONFLICT);

        //HASH password + salt(email(unique)) arranjar soluao melhorada
        String hash = PasswordUtil.create(account.getPassword());
        account.setPassword(hash);

        Account acc = accountsRepository.save(account);
        return ok(acc.getId());
    }

    @Override
    public Result<Account> getAccount(Long id) {
        Account acc = accountsRepository.getAccountById(id);
        if (acc == null)
            return error(NOT_FOUND);
        acc.setPassword(null);
        return ok(acc);
    }

    @Override
    public Result<Void> deleteAccount(Long id) {
        Account acc = accountsRepository.getAccountById(id);
        if (acc == null)
            return error(NOT_FOUND);

        //Deixa de ter visto e de ter sido visto
        profileViewersRepository.deleteAllByViewerId(id);
        profileViewersRepository.deleteAllByProfileId(id);

        //Deixa de seguir e de ser seguido
        followersRepository.deleteAllByAccountId(id);
        followersRepository.deleteAllByIsFollowingId(id);

        //Apagar todas as publicações da conta
        publicationRepository.deleteAllByOwnerId(id);

        //Apagar comentarios e reacoes
        commentRepository.deleteAllByUserId(id);
        reactionsRepository.deleteAllByUserId(id);

        accountsRepository.deleteById(id);
        return ok();
    }

    @Override
    public Result<Void> setNewViewer(Long thisAccount, Long seeThatAccount) {
        if (thisAccount.equals(seeThatAccount)) { //NAO CONTA VER O PROPRIO PERFIL
            ProfileViewer pv = new ProfileViewer(thisAccount, seeThatAccount);
            profileViewersRepository.save(pv);
        }
        return ok();
    }

    @Override
    public Result<List<ProfileViewer>> getAccountViweres(Long id) {
        List<ProfileViewer> list = profileViewersRepository.getAllByProfileId(id);
        if (list == null)
            return error(NOT_FOUND);
        return ok(list);
    }

    @Override
    public Result<Void> followAccount(Follower follower) {
        Long fromId = follower.getAccountId();
        Long toId = follower.getIsFollowingId();
        if (!accountExists(fromId) || !accountExists(toId))
            return error(NOT_FOUND);

        Follower fol = followersRepository.getByAccountIdAndIsFollowingId(fromId, toId);
        if (fol != null)
            return error(CONFLICT);

        follower.setFollowDate();
        followersRepository.save(follower);
        return ok();
    }

    @Override
    public Result<Void> unfollowAccount(Follower follower) {
        Long fromId = follower.getAccountId();
        Long toId = follower.getIsFollowingId();
        if (!accountExists(fromId) || !accountExists(toId))
            return error(NOT_FOUND);
        followersRepository.deleteByAccountIdAndIsFollowingId(follower.getAccountId(), follower.getIsFollowingId());
        return ok();
    }

    @Override
    public Result<List<Follower>> getFollowersAccount(Long id) {
        if (!accountExists(id))
            return error(NOT_FOUND);
        List<Follower> list = followersRepository.getAllByIsFollowingId(id);
        return ok(list);
    }

    @Override
    public Result<List<Publication>> getFeed(Long id) {
        if (!accountExists(id))
            return error(NOT_FOUND);
        List<Publication> result = new LinkedList<>();
        List<Follower> list = followersRepository.getAllByAccountId(id);

        for (Follower following : list) {
            Long followingId = following.getIsFollowingId();
            List<Publication> followingPublications = publicationRepository.getAllPublicationsByOwnerId(followingId);
            result.addAll(followingPublications);
        }

        result.addAll(publicationRepository.getAllPublicationsByOwnerId(id));

        result.sort(new Comparator<Publication>() {
            DateFormat f = new SimpleDateFormat(DateUtil.DATE_PATTERN);

            @Override
            public int compare(Publication o1, Publication o2) {
                try {
                    return f.parse(o1.getPublicationDate()).compareTo(f.parse(o2.getPublicationDate()));
                } catch (ParseException e) {
                    return 0;
                }
            }
        });

        return ok(result);
    }

    @Override
    public Result<Map<Long, List<Stories>>> getStoryFeed(Long id) {
        if (!accountExists(id))
            return error(NOT_FOUND);
        Map<Long, List<Stories>> result = new HashMap<>();
        List<Follower> list = followersRepository.getAllByAccountId(id);

        for (Follower following : list) {
            Long followingId = following.getIsFollowingId();
            List<Stories> followingStories = storiesRepository.getAllByOwnerId(followingId);
            for (Stories s : followingStories)
                if (s.isExpired()) {
                    storiesRepository.delete(s);
                    followingStories.remove(s);
                }
            followingStories.sort(new Comparator<Stories>() {
                DateFormat f = new SimpleDateFormat(DateUtil.DATE_PATTERN);

                @Override
                public int compare(Stories o1, Stories o2) {
                    try {
                        return f.parse(o1.getPublicationDate()).compareTo(f.parse(o2.getPublicationDate()));
                    } catch (ParseException e) {
                        return 0;
                    }
                }
            });
            result.put(followingId, followingStories);
        }
        return ok(result);
    }

    @Override
    public Result<List<Follower>> getAccountFollowings(Long id) {
        if (!accountExists(id))
            return error(NOT_FOUND);
        List<Follower> list = followersRepository.getAllByAccountId(id);
        return ok(list);
    }

    @Override
    public Result<Void> changeVisibility(Long accountId) {
        Account acc = accountsRepository.getAccountById(accountId);
        if (acc == null)
            return error(NOT_FOUND);
        acc.chengeVisibility();
        accountsRepository.save(acc);
        return ok();
    }

    private boolean accountExists(Long id) {
        return accountsRepository.getAccountById(id) != null;
    }

}
