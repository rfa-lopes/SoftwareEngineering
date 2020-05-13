package fct.unl.pt.instagramplus.Repositories.Publications;

import fct.unl.pt.instagramplus.Models.Publications.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationsRepository extends JpaRepository<Publication, Long> {

    Publication getPublicationById(Long id);
}
