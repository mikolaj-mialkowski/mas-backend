package pjatk.mas_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pjatk.mas_backend.models.entities.SpeciesEntity;
import pjatk.mas_backend.models.entities.UndemandingPlantEntity;

import java.util.List;

@Repository
public interface UndemandingPlantRepository extends JpaRepository<UndemandingPlantEntity, Long> {

}
