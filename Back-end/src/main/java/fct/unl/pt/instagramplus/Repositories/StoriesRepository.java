package fct.unl.pt.instagramplus.Repositories;

import fct.unl.pt.instagramplus.Models.Publications.Publication;
import fct.unl.pt.instagramplus.Models.Stories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoriesRepository extends JpaRepository<Stories, Long> {
    Stories getStoriesById(Long id);
    List<Stories> getAllByOwnerId(Long id);
}
