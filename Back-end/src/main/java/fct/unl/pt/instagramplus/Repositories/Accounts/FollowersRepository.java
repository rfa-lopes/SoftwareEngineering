package fct.unl.pt.instagramplus.Repositories.Accounts;

import fct.unl.pt.instagramplus.Models.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FollowersRepository extends JpaRepository<Follower, Long> {

    Follower getFirstById(Long id);

    List<Follower> getAllByIsFollowingId(Long id);  //Ver quem segue o id

    List<Follower> getAllByAccountId(Long id);      //Ver quem o id segue

    int countAllByIsFollowingId(Long id); //Ver quantos seguem o id

    int countAllByAccountId(Long id); //Ver quantos so id segue

    Follower getByAccountIdAndIsFollowingId(Long accountId, Long isFollowingId);

    @Transactional
    void deleteByAccountIdAndIsFollowingId(Long accountId, Long isFollowingId);

    void deleteAllByAccountId(Long id1);

    void deleteAllByIsFollowingId(Long id1);

}
