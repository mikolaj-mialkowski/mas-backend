package pjatk.mas_backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pjatk.mas_backend.models.business.DemandingPlantBO;
import pjatk.mas_backend.models.entities.DemandingPlantEntity;
import pjatk.mas_backend.models.entities.UndemandingPlantEntity;
import pjatk.mas_backend.models.enums.HealthState;
import pjatk.mas_backend.models.exceptions.BusinessException;
import pjatk.mas_backend.repositories.DemandingPlantRepository;
import pjatk.mas_backend.repositories.SpeciesRepository;
import pjatk.mas_backend.repositories.UndemandingPlantRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DemandingPlantService {

    private final static Logger LOGGER = LoggerFactory.getLogger(DemandingPlantService.class);

    private final DemandingPlantRepository demandingPlantRepository;

    private final UndemandingPlantRepository undemandingPlantRepository;

    public DemandingPlantService(DemandingPlantRepository demandingPlantRepository
            , UndemandingPlantRepository undemandingPlantRepository)
    {
        this.demandingPlantRepository = demandingPlantRepository;
        this.undemandingPlantRepository = undemandingPlantRepository;
    }


    public DemandingPlantBO getDemandingPlantById(Long id){
        DemandingPlantEntity demandingPlantEntity = demandingPlantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No demanding plant found, for id = " + id ));

        return entityToBusinessObject(demandingPlantEntity);
    }

    public List<DemandingPlantBO> getAllDemandingPlants(){
        List<DemandingPlantEntity> demandingPlantEntities = demandingPlantRepository.findAll();

        if (demandingPlantEntities.isEmpty())
            throw new ResourceNotFoundException("No demanding plants found");

        return demandingPlantEntities.stream().map(this::entityToBusinessObject).collect(Collectors.toList());
    }

    public DemandingPlantBO saveDemandingPlant(DemandingPlantBO demandingPlantBO){

        if (demandingPlantBO.getId() == null)
            throw new BusinessException("Id of undemanding plant is required");

        UndemandingPlantEntity undemandingPlantEntityToRemove = undemandingPlantRepository.
                findById(demandingPlantBO.getId())
                .orElseThrow(() -> new BusinessException("No such undemanding plant found"));

        DemandingPlantBO demandingPlantBOToAdd = DemandingPlantBO.builder()
                .fertilizer(demandingPlantBO.getFertilizer())
                .groundPH(demandingPlantBO.getGroundPH())
                .hydratingDays(demandingPlantBO.getHydratingDays())
                .speciesEntity(undemandingPlantEntityToRemove.getSpeciesEntity())
                .build();

        DemandingPlantEntity demandingPlantEntityToAdd = demandingPlantRepository.
                saveAndFlush(businessObjectToEntity(demandingPlantBOToAdd));

        undemandingPlantRepository.delete(undemandingPlantEntityToRemove);

        LOGGER.info("Saved new demanding plant, as demanding plant entity = " + demandingPlantEntityToAdd);
        LOGGER.info("Deleted undemanding plant, as undemanding plant entity = " + undemandingPlantEntityToRemove);
        return entityToBusinessObject(demandingPlantEntityToAdd);
    }

    private DemandingPlantEntity businessObjectToEntity(DemandingPlantBO demandingPlantBO){
        return DemandingPlantEntity.builder()
                .fertilizer(demandingPlantBO.getFertilizer())
                .healthState(HealthState.UNHEALTHY_DEMANDING)
                .speciesEntity(demandingPlantBO.getSpeciesEntity())
                .groundPH(demandingPlantBO.getGroundPH())
                .hydratingDays(demandingPlantBO.getHydratingDays())
                .build();
    }

    @SuppressWarnings("all")
    private DemandingPlantBO entityToBusinessObject(DemandingPlantEntity demandingPlantEntity){
        return DemandingPlantBO.builder()
                .id(demandingPlantEntity.getId())
                .fertilizer(demandingPlantEntity.getFertilizer())
                .speciesEntity(demandingPlantEntity.getSpeciesEntity())
                .groundPH(demandingPlantEntity.getGroundPH())
                .hydratingDays(demandingPlantEntity.getHydratingDays())
                .careEntity(demandingPlantEntity.getCareEntity())
                .build();
    }
}
