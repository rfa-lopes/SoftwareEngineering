package fct.unl.pt.instagramplus.Repositories;

import fct.unl.pt.instagramplus.Models.Publications.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoriesRepository extends JpaRepository<Publication, Long> {
    Publication getStoryById(Long id);
    List<Publication> getAllByOwnerId(Long id);
}
