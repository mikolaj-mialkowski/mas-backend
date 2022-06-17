package pjatk.mas_backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pjatk.mas_backend.models.business.ExperiencedGardenerBO;
import pjatk.mas_backend.models.entities.ExperiencedGardenerEntity;
import pjatk.mas_backend.models.exceptions.BusinessException;
import pjatk.mas_backend.repositories.ExperiencedGardenerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExperiencedGardenerService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ExperiencedGardenerService.class);

    private final ExperiencedGardenerRepository experiencedGardenerRepository;

    public ExperiencedGardenerService(ExperiencedGardenerRepository experiencedGardenerRepository) {
        this.experiencedGardenerRepository = experiencedGardenerRepository;
    }


    public ExperiencedGardenerBO getExperiencedGardenerById(Long id){
        ExperiencedGardenerEntity experiencedGardenerEntity = experiencedGardenerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No experienced gardener found, for id = " + id ));

        return entityToBusinessObject(experiencedGardenerEntity);
    }

    public List<ExperiencedGardenerBO> getAllExperiencedGardeners(){
        List<ExperiencedGardenerEntity> experiencedGardenerEntityList = experiencedGardenerRepository.findAll();

        if (experiencedGardenerEntityList.isEmpty())
            throw new ResourceNotFoundException("No experienced gardener found");

        return experiencedGardenerEntityList.stream().map(this::entityToBusinessObject).collect(Collectors.toList());
    }

    public ExperiencedGardenerBO saveExperiencedGardener(ExperiencedGardenerBO experiencedGardenerBO){

        experiencedGardenerRepository.findAllByPesel(experiencedGardenerBO.getPesel()).stream()
                .findAny().ifPresent( (duplicate) -> {
                    throw new BusinessException("Pesel values have to be unique");
                });

        ExperiencedGardenerEntity experiencedGardenerEntity = experiencedGardenerRepository.
                saveAndFlush(businessObjectToEntity(experiencedGardenerBO));

        LOGGER.info("Saved new experienced gardener, as experienced gardener entity = " + experiencedGardenerEntity);
        return entityToBusinessObject(experiencedGardenerEntity);
    }

    private ExperiencedGardenerEntity businessObjectToEntity(ExperiencedGardenerBO experiencedGardenerBO){
        return ExperiencedGardenerEntity.builder()
                .firstName(experiencedGardenerBO.getFirstName())
                .lastName(experiencedGardenerBO.getLastName())
                .pesel(experiencedGardenerBO.getPesel())
                .birthDate(experiencedGardenerBO.getBirthDate())
                .employmentDate(experiencedGardenerBO.getEmploymentDate())
                .salary(experiencedGardenerBO.getSalary())
                .contactInfo(experiencedGardenerBO.getContactInfo())
                .salaryBonus(experiencedGardenerBO.getSalaryBonus())
                .promotionDate(experiencedGardenerBO.getPromotionDate())
                .build();
    }

    @SuppressWarnings("all")
    private ExperiencedGardenerBO entityToBusinessObject(ExperiencedGardenerEntity experiencedGardenerEntity){
        return ExperiencedGardenerBO.builder()
                .id(experiencedGardenerEntity.getId())
                .firstName(experiencedGardenerEntity.getFirstName())
                .lastName(experiencedGardenerEntity.getLastName())
                .pesel(experiencedGardenerEntity.getPesel())
                .birthDate(experiencedGardenerEntity.getBirthDate())
                .employmentDate(experiencedGardenerEntity.getEmploymentDate())
                .salary(experiencedGardenerEntity.getSalary())
                .contactInfo(experiencedGardenerEntity.getContactInfo())
                .salaryBonus(experiencedGardenerEntity.getSalaryBonus())
                .promotionDate(experiencedGardenerEntity.getPromotionDate())
                .careEntity(experiencedGardenerEntity.getCareEntity())
                .build();
    }
}
