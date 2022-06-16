package pjatk.mas_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pjatk.mas_backend.models.entities.AdministrationWorkerEntity;

@Repository
public interface AdministrationWorkerRepository extends JpaRepository<AdministrationWorkerEntity, Long> {
}
