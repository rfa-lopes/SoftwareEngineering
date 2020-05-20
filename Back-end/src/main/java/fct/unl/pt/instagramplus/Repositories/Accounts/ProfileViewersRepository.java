package fct.unl.pt.instagramplus.Repositories.Accounts;

import fct.unl.pt.instagramplus.Models.ProfileViewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface ProfileViewersRepository extends JpaRepository<ProfileViewer, Long> {

    ProfileViewer getProfileViewerById(Long id);

    List<ProfileViewer> getAllByProfileId(Long profileId);

    void deleteAllByViewerId(Long id1);

    void deleteAllByProfileId(Long id1);

}
