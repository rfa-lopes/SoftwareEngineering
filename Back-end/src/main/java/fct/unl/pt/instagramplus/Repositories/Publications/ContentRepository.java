package fct.unl.pt.instagramplus.Repositories.Publications;

import fct.unl.pt.instagramplus.Models.Publications.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {

    Content getContentById(Long id);

}
