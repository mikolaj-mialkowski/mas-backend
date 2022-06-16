package pjatk.mas_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pjatk.mas_backend.models.entities.ClientEntity;
import pjatk.mas_backend.models.entities.VisitEntity;
import pjatk.mas_backend.models.enums.ClientType;

import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<VisitEntity, Long> {

}
