package pjatk.mas_backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pjatk.mas_backend.models.business.AdministrationWorkerBO;
import pjatk.mas_backend.models.entities.AdministrationWorkerEntity;
import pjatk.mas_backend.models.exceptions.BusinessException;
import pjatk.mas_backend.repositories.AdministrationWorkerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdministrationWorkerService {

    private final static Logger LOGGER = LoggerFactory.getLogger(AdministrationWorkerService.class);

    private final AdministrationWorkerRepository administrationWorkerRepository;

    public AdministrationWorkerService(AdministrationWorkerRepository administrationWorkerRepository) {
        this.administrationWorkerRepository = administrationWorkerRepository;
    }

    public AdministrationWorkerBO getAdministrationWorkerById(Long id){
        AdministrationWorkerEntity administrationWorkerEntity = administrationWorkerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No administration worker found, for id = " + id ));

        return entityToBusinessObject(administrationWorkerEntity);
    }

    public List<AdministrationWorkerBO> getAllAdministrationWorkers(){
        List<AdministrationWorkerEntity> administrationWorkerEntityList = administrationWorkerRepository.findAll();

        if (administrationWorkerEntityList.isEmpty())
            throw new ResourceNotFoundException("No administration workers found");

        return administrationWorkerEntityList.stream().map(this::entityToBusinessObject).collect(Collectors.toList());
    }

    public AdministrationWorkerBO saveAdministrationWorker(AdministrationWorkerBO administrationWorkerBO){

        administrationWorkerRepository.findAllByPesel(administrationWorkerBO.getPesel()).stream()
                .findAny().ifPresent( (duplicate) -> {
                    throw new BusinessException("Pesel values have to be unique");
                });

        AdministrationWorkerEntity administrationWorkerEntity = administrationWorkerRepository
                .saveAndFlush(businessObjectToEntity(administrationWorkerBO));

        LOGGER.info("Saved new administration worker, as administration worker entity = " + administrationWorkerEntity);
        return entityToBusinessObject(administrationWorkerEntity);
    }

    private AdministrationWorkerEntity businessObjectToEntity(AdministrationWorkerBO administrationWorkerBO){
        return AdministrationWorkerEntity.builder()
                .firstName(administrationWorkerBO.getFirstName())
                .lastName(administrationWorkerBO.getLastName())
                .pesel(administrationWorkerBO.getPesel())
                .birthDate(administrationWorkerBO.getBirthDate())
                .employmentDate(administrationWorkerBO.getEmploymentDate())
                .salary(administrationWorkerBO.getSalary())
                .contactInfo(administrationWorkerBO.getContactInfo())
                .build();
    }

    @SuppressWarnings("all")
    private AdministrationWorkerBO entityToBusinessObject(AdministrationWorkerEntity administrationWorkerEntity){
        return AdministrationWorkerBO.builder()
                .id(administrationWorkerEntity.getId())
                .firstName(administrationWorkerEntity.getFirstName())
                .lastName(administrationWorkerEntity.getLastName())
                .pesel(administrationWorkerEntity.getPesel())
                .birthDate(administrationWorkerEntity.getBirthDate())
                .employmentDate(administrationWorkerEntity.getEmploymentDate())
                .salary(administrationWorkerEntity.getSalary())
                .contactInfo(administrationWorkerEntity.getContactInfo())
                .build();
    }
}
