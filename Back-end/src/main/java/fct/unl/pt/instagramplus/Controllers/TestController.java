package fct.unl.pt.instagramplus.Controllers;

import fct.unl.pt.instagramplus.Models.*;
import fct.unl.pt.instagramplus.Models.Publications.Publication;
import fct.unl.pt.instagramplus.Models.Reactions.Reaction;
import fct.unl.pt.instagramplus.Models.Reactions.ReactionType;
import fct.unl.pt.instagramplus.Repositories.Accounts.AccountsRepository;
import fct.unl.pt.instagramplus.Repositories.Accounts.FollowersRepository;
import fct.unl.pt.instagramplus.Repositories.Accounts.ProfileViewersRepository;
import fct.unl.pt.instagramplus.Repositories.CommentsRepository;
import fct.unl.pt.instagramplus.Repositories.MessagesRepository;
import fct.unl.pt.instagramplus.Repositories.Publications.PublicationsRepository;
import fct.unl.pt.instagramplus.Repositories.ReactionsRepository;
import fct.unl.pt.instagramplus.Repositories.StoriesRepository;
import fct.unl.pt.instagramplus.Utils.DateUtil;
import fct.unl.pt.instagramplus.Utils.Logger;
import fct.unl.pt.instagramplus.Utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = TestController.BASE_URL)
public class TestController {

    static final String BASE_URL = "/tests";

    static boolean initialized = false;

    @Autowired
    AccountsRepository accountsRepository;

    @Autowired
    ProfileViewersRepository profileViewersRepository;

    @Autowired
    FollowersRepository followersRepository;

    @Autowired
    MessagesRepository messagesRepository;

    @Autowired
    PublicationsRepository publicationRepository;

    @Autowired
    CommentsRepository commentRepository;

    @Autowired
    ReactionsRepository reactionsRepository;

    @Autowired
    StoriesRepository storiesRepository;

    @GetMapping(
            value = "/test1/{nrAcc}/{maxPubs}",
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<String> test1(
            @PathVariable("nrAcc") int nrAcc,
            @PathVariable("maxPubs") int maxPubs) {

        Logger.info("REQUEST: DATABASE INITIALIZE");

        if (initialized) return ResponseEntity.ok("Already initialized!");

        try {

            if (nrAcc > 20) nrAcc = 20;
            if (maxPubs > 10) maxPubs = 10;

            int nAccounts = nrAcc;
            int minPubsPerAccount = 0;
            int maxPubsPerAccount = maxPubs;

            //ADICIONAR X CONTAS
            addUsers(nAccounts);
            Logger.info("Accounts: " + nAccounts);

            Random r = new Random();

            //CRIAR Y(RANDOM) PUBLICACOES A CADA CONTA
            int nPublicationsInAccount;
            int nPublicationsAll = 0;
            for (int i = 1; i <= nAccounts; i++) {
                nPublicationsInAccount = r.nextInt(maxPubsPerAccount - minPubsPerAccount) + minPubsPerAccount;
                addPublications(i, nPublicationsInAccount);
                nPublicationsAll += nPublicationsInAccount;
            }
            Logger.info("Publications: " + nPublicationsAll);

            //CRIAR Y(RANDOM) STORIES A CADA CONTA
            int nStoriesInAccount;
            int nStoriesAll = 0;
            for (int i = 1; i <= nAccounts; i++) {
                nStoriesInAccount = r.nextInt(maxPubsPerAccount - minPubsPerAccount) + minPubsPerAccount;
                addStories(i, nStoriesInAccount);
                nStoriesAll += nStoriesInAccount;
            }
            Logger.info("Stories: " + nStoriesAll);

            //METER AS CONTAS A SEGUIREM SE UMAS AS OUTRAS DE FORMA RANDOM
            int followers = 0;
            for (int i = 1; i <= nAccounts; i++) {
                for (int j = 1; j <= nAccounts; j++) {
                    if (i == j) continue;
                    if (r.nextBoolean()) {
                        addFollower(i, j);
                        followers++;
                    }
                }
            }
            Logger.info("Followers: " + followers);

            //METER AS CONTAS A VEREM SE UMAS AS OUTRAS DE FORMA RANDOM
            int views = 0;
            for (int i = 1; i <= nAccounts; i++) {
                for (int j = 1; j <= nAccounts; j++) {
                    if (i == j) continue;
                    if (r.nextBoolean()) {
                        addProfileViewr(i, j);
                        views++;
                    }
                }
            }
            Logger.info("Views: " + views);

            //METER AS CONTAS A COMENTAR TODAS AS PUBLICACOES DE FORMA RANDOM
            int comments = 0;
            for (int i = 1; i <= nAccounts; i++)
                for (int j = nAccounts + 1; j <= nAccounts + nPublicationsAll; j++)
                    if (r.nextBoolean()) {
                        addCommentToPublication(i, j);
                        comments++;
                    }
            Logger.info("Comments: " + comments);


            //METER AS CONTAS A REAGIR TODAS AS PUBLICACOES DE FORMA RANDOM
            int reactions = 0;
            for (int i = 1; i <= nAccounts; i++)
                for (int j = nAccounts + 1; j <= nAccounts + nPublicationsAll; j++)
                    if (r.nextBoolean()) {
                        int like = r.nextInt(5 - 0) + 0;
                        addReactionToPublication(i, j, like);
                        reactions++;
                    }
            Logger.info("Reactions: " + reactions);

            //METER AS CONTAS A MANDAREM MENSAGENS UMAS AS OUTRAS DE FORMA RANDOM
            int messages = 0;
            for (int i = 1; i <= nAccounts; i++) {
                for (int j = 1; j <= nAccounts; j++) {
                    if (i == j) continue;
                    if (r.nextBoolean()) {
                        addMessages(i, j);
                        messages++;
                    }
                }
            }
            Logger.info("Messages: " + messages);
            Logger.info("Init data base concluded.");

            initialized = true;
            return ResponseEntity.ok("DATABASE INITIALIZE WITH: " +
                    "\nAccounts: " + nAccounts +
                    "\nPublications: " + nPublicationsAll + "" +
                    "\nStories: " + nStoriesAll + "" +
                    "\nFollowers: " + followers + "" +
                    "\nViews: " + views + "" +
                    "\nComments: " + comments + "" +
                    "\nReactions: " + reactions + "" +
                    "\nMessages: " + messages + "" +
                    "\n\nCreds - username1:password1" +
                    "");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Error");
        }
    }

    private void addUsers(int nAccounts) {
        for (int i = 1; i <= nAccounts; i++) {
            String passwordHash = PasswordUtil.create("password" + i);
            accountsRepository.save(new Account("username" + i, passwordHash, "name" + i, "email" + i));
        }
    }

    private void addPublications(int ownerId, int nPublications) {
        for (int i = 1; i <= nPublications; i++) {
            Publication p = new Publication(Long.valueOf(ownerId), "Publication: " + i + "; ownerId: " + ownerId);
            long leftLimit = 920073600000L; //1999/02/27
            long rightLimit = System.currentTimeMillis();
            long r = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
            p.setPublicationDate(DateUtil.getDate(r));
            publicationRepository.save(p);
        }
    }

    private void addStories(int ownerId, int nStories) {
        for (int i = 1; i <= nStories; i++){
            Stories s = new Stories(Long.valueOf(ownerId));
            long leftLimit = System.currentTimeMillis()-86400000; //-24h
            long rightLimit = System.currentTimeMillis();
            long r = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
            s.setPublicationDate(DateUtil.getDate(r));
            s.setExpireDate(DateUtil.getDate(r-86400000));
            storiesRepository.save(s);
        }
    }

    private void addFollower(int thisAccount, int followThisAccount) {
        followersRepository.save(new Follower(Long.valueOf(thisAccount), Long.valueOf(followThisAccount)));
    }

    private void addProfileViewr(int thisAccount, int viewThisAccount) {
        profileViewersRepository.save(new ProfileViewer(Long.valueOf(thisAccount), Long.valueOf(viewThisAccount)));
    }

    private void addCommentToPublication(int accountId, int publicatioId) {
        commentRepository.save(new Comment(Long.valueOf(accountId), Long.valueOf(publicatioId), "Comment from user: " + accountId + " in publication: " + publicatioId));
    }

    private void addReactionToPublication(int accountId, int publicatioId, int likeId) {
        Reaction reaction = new Reaction();
        reaction.setUserId(Long.valueOf(accountId));
        reaction.setPublicationId(Long.valueOf(publicatioId));
        reaction.setReactionDate(DateUtil.getAtualDate());
        switch (likeId) {
            case 1:
                reaction.setType(ReactionType.LOVE);
                break;
            case 2:
                reaction.setType(ReactionType.AHAH);
                break;
            case 3:
                reaction.setType(ReactionType.WOW);
                break;
            case 4:
                reaction.setType(ReactionType.ANGRY);
                break;
            case 5:
                reaction.setType(ReactionType.SAD);
                break;
            default:
                reaction.setType(ReactionType.LIKE);
                break;
        }
        reactionsRepository.save(reaction);
    }

    private void addMessages(int from, int to) {
        messagesRepository.save(new Message(Long.valueOf(from), Long.valueOf(to), "Message from: " + from + " to: " + to));
    }


}
