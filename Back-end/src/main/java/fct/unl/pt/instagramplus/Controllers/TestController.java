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
import fct.unl.pt.instagramplus.Utils.DateUtil;
import fct.unl.pt.instagramplus.Utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = TestController.BASE_URL)
public class TestController {

    static final String BASE_URL = "/tests";


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

    @GetMapping(value = "/test1", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<String> test1() {
        Logger.info("REQUEST: DATABASE INITIALIZE");

        int nAccounts = 10;
        int minPubsPerAccount = 1;
        int maxPubsPerAccount = 5;

        //ADICIONAR X CONTAS
        addUsers(nAccounts);

        //CRIAR Y(RANDOM) PUBLICACOES A CADA CONTA ADICIONA
        Random r = new Random();
        int nPublicationsInAccount;
        int nPublicationsAll = 0;
        for (int i = 1; i <= nAccounts; i++) {
            nPublicationsInAccount = r.nextInt(maxPubsPerAccount - minPubsPerAccount) + minPubsPerAccount;
            addPublications(i, nPublicationsInAccount);
            nPublicationsAll += nPublicationsInAccount;
        }

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

        //METER AS CONTAS A COMENTAR TODAS AS PUBLICACOES DE FORMA RANDOM
        int comments = 0;
        for (int i = 1; i <= nAccounts; i++)
            for (int j = nAccounts + 1; j <= nAccounts + nPublicationsAll; j++)
                if (r.nextBoolean()) {
                    addCommentToPublication(i, j);
                    comments++;
                }


        //METER AS CONTAS A REAGIR TODAS AS PUBLICACOES DE FORMA RANDOM
        int reactions = 0;
        for (int i = 1; i <= nAccounts; i++)
            for (int j = nAccounts + 1; j <= nAccounts + nPublicationsAll; j++)
                if (r.nextBoolean()) {
                    int like = r.nextInt(5 - 0) + 0;
                    addReactionToPublication(i, j, like);
                    reactions++;
                }

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

        return ResponseEntity.ok("DATABASE INITIALIZE WITH: " +
                "\nAccounts: " + nAccounts +
                "\nPublications: " + nPublicationsAll + "" +
                "\nFollowers: " + followers + "" +
                "\nViews: " + views + "" +
                "\nComments: " + comments + "" +
                "\nReactions: " + reactions + "" +
                "\nMessages: " + messages + "" +
                "");
    }

    private void addUsers(int nAccounts) {
        for (int i = 1; i <= nAccounts; i++)
            accountsRepository.save(new Account("username" + i, "password" + i, "name" + i, "email" + i));
    }

    private void addPublications(int ownerId, int nPublications) {
        for (int i = 1; i <= nPublications; i++)
            publicationRepository.save(new Publication(Long.valueOf(ownerId), "Publication: " + i + "; ownerId: " + ownerId));
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
        messagesRepository.save(new Message(Long.valueOf(from), Long.valueOf(to), "Message from: "+from+" to: "+ to));
    }



}
