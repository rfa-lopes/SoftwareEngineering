package fct.unl.pt.instagramplus.Repositories;

import fct.unl.pt.instagramplus.Models.ProfileViewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProfileViewersRepository extends JpaRepository<ProfileViewer, Long> {

    ProfileViewer getProfileViewerBy(Long id);

    List<ProfileViewer> getAllByProfileId(Long profileId);

}
