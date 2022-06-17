package pjatk.mas_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pjatk.mas_backend.models.entities.ArticleEntity;
import pjatk.mas_backend.models.entities.CareEntity;

@Repository
public interface CareRepository extends JpaRepository<CareEntity, Long> {

}
