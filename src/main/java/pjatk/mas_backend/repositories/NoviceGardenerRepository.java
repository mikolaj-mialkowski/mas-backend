package pjatk.mas_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pjatk.mas_backend.models.entities.NoviceGardenerEntity;

import java.util.List;

@Repository
public interface NoviceGardenerRepository extends JpaRepository<NoviceGardenerEntity, Long> {

    List<NoviceGardenerEntity> findAllByPesel(Long pesel);
}
