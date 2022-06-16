package pjatk.mas_backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pjatk.mas_backend.models.business.NoviceGardenerBO;
import pjatk.mas_backend.models.entities.NoviceGardenerEntity;
import pjatk.mas_backend.models.exceptions.BusinessException;
import pjatk.mas_backend.repositories.NoviceGardenerRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class NoviceGardenerService {

    private final static Logger LOGGER = LoggerFactory.getLogger(NoviceGardenerService.class);
    private final NoviceGardenerRepository noviceGardenerRepository;

    public NoviceGardenerService(NoviceGardenerRepository noviceGardenerRepository) {
        this.noviceGardenerRepository = noviceGardenerRepository;
    }

    public NoviceGardenerBO getNoviceGardenerById(Long id){
        NoviceGardenerEntity noviceGardenerEntity = noviceGardenerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No novice gardener found, for id = " + id ));

        return entityToBusinessObject(noviceGardenerEntity);
    }

    public List<NoviceGardenerBO> getAllNoviceGardeners(){
        List<NoviceGardenerEntity> noviceGardenerEntityList = noviceGardenerRepository.findAll();

        if (noviceGardenerEntityList.isEmpty())
            throw new ResourceNotFoundException("No novice gardeners found");

        return noviceGardenerEntityList.stream().map(this::entityToBusinessObject).collect(Collectors.toList());
    }

    public NoviceGardenerBO saveNoviceGardener(NoviceGardenerBO noviceGardenerBO){

        noviceGardenerRepository.findAll().stream()
                .filter(noviceGardenerEntity -> noviceGardenerEntity.getPesel().equals(noviceGardenerBO.getPesel()))
                .findAny().ifPresent( (duplicate) -> {throw new BusinessException("Pesel values have to be unique");});

        NoviceGardenerEntity noviceGardenerEntity = noviceGardenerRepository.saveAndFlush(businessObjectToEntity(noviceGardenerBO));


        LOGGER.info("Saved new novice gardener, as novice gardener entity = " + noviceGardenerEntity);
        return entityToBusinessObject(noviceGardenerEntity);

    }

    private NoviceGardenerEntity businessObjectToEntity(NoviceGardenerBO noviceGardenerBO){
        return NoviceGardenerEntity.builder()
                .firstName(noviceGardenerBO.getFirstName())
                .lastName(noviceGardenerBO.getLastName())
                .pesel(noviceGardenerBO.getPesel())
                .birthDate(noviceGardenerBO.getBirthDate())
                .employmentDate(noviceGardenerBO.getEmploymentDate())
                .salary(noviceGardenerBO.getSalary())
                .contactInfo(noviceGardenerBO.getContactInfo())
                .build();
    }

    @SuppressWarnings("all")
    private NoviceGardenerBO entityToBusinessObject(NoviceGardenerEntity noviceGardenerEntity){
        return NoviceGardenerBO.builder()
                .id(noviceGardenerEntity.getId())
                .firstName(noviceGardenerEntity.getFirstName())
                .lastName(noviceGardenerEntity.getLastName())
                .pesel(noviceGardenerEntity.getPesel())
                .birthDate(noviceGardenerEntity.getBirthDate())
                .employmentDate(noviceGardenerEntity.getEmploymentDate())
                .salary(noviceGardenerEntity.getSalary())
                .contactInfo(noviceGardenerEntity.getContactInfo())
                .build();
    }
}
