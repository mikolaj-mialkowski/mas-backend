package pjatk.mas_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pjatk.mas_backend.models.entities.GardenEntity;
import pjatk.mas_backend.models.entities.SpeciesEntity;

import java.util.List;

@Repository
public interface GardenRepository extends JpaRepository<GardenEntity, Long> {

}
