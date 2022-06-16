package pjatk.mas_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pjatk.mas_backend.models.entities.DemandingPlantEntity;

@Repository
public interface DemandingPlantRepository extends JpaRepository<DemandingPlantEntity, Long> {

}
