package pjatk.mas_backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pjatk.mas_backend.models.business.NoviceGardenerBO;
import pjatk.mas_backend.models.business.SpeciesBO;
import pjatk.mas_backend.models.entities.NoviceGardenerEntity;
import pjatk.mas_backend.models.entities.SpeciesEntity;
import pjatk.mas_backend.models.exceptions.BusinessException;
import pjatk.mas_backend.repositories.SpeciesRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpeciesService {

    private final static Logger LOGGER = LoggerFactory.getLogger(SpeciesService.class);

    private final SpeciesRepository speciesRepository;

    public SpeciesService(SpeciesRepository speciesRepository) {
        this.speciesRepository = speciesRepository;
    }


    public SpeciesBO getSpeciesById(Long id){
        SpeciesEntity speciesEntity = speciesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No species found, for id = " + id ));

        return entityToBusinessObject(speciesEntity);
    }

    public List<SpeciesBO> getAllSpecies(){
        List<SpeciesEntity> speciesBOList = speciesRepository.findAll();

        if (speciesBOList.isEmpty())
            throw new ResourceNotFoundException("No species found");

        return speciesBOList.stream().map(this::entityToBusinessObject).collect(Collectors.toList());
    }

    public SpeciesBO saveSpecies(SpeciesBO speciesBO){

        speciesRepository.findAllByLatinName(speciesBO.getLatinName()).stream()
                .findAny().ifPresent( (duplicate) -> {
                    throw new BusinessException("Latin names have to be unique");
                });

        SpeciesEntity speciesEntity = speciesRepository.
                saveAndFlush(businessObjectToEntity(speciesBO));

        LOGGER.info("Saved new species, as species entity = " + speciesEntity);
        return entityToBusinessObject(speciesEntity);
    }

    private SpeciesEntity businessObjectToEntity(SpeciesBO speciesBO){
        return SpeciesEntity.builder()
                .latinName(speciesBO.getLatinName())
                .lifeCycle(speciesBO.getLifeCycle())
                .build();
    }

    @SuppressWarnings("all")
    private SpeciesBO entityToBusinessObject(SpeciesEntity speciesEntity){
        return SpeciesBO.builder()
                .id(speciesEntity.getId())
                .latinName(speciesEntity.getLatinName())
                .lifeCycle(speciesEntity.getLifeCycle())
                .build();
    }
}
