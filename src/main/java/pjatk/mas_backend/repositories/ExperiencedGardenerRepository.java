package pjatk.mas_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pjatk.mas_backend.models.entities.ExperiencedGardenerEntity;


@Repository
public interface ExperiencedGardenerRepository extends JpaRepository<ExperiencedGardenerEntity, Long> {
}
