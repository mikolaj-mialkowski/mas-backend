package pjatk.mas_backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pjatk.mas_backend.models.business.ArticleBO;
import pjatk.mas_backend.models.business.CareBO;
import pjatk.mas_backend.models.entities.*;
import pjatk.mas_backend.models.exceptions.BusinessException;
import pjatk.mas_backend.repositories.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CareService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CareService.class);

    private final CareRepository careRepository;
    private final ArticleRepository articleRepository;
    private final ExperiencedGardenerRepository experiencedGardenerRepository;

    private final DemandingPlantRepository demandingPlantRepository;

    public CareService(CareRepository careRepository
            , ArticleRepository articleRepository
            , ExperiencedGardenerRepository gardenRepository
            , DemandingPlantRepository demandingPlantRepository)
    {
        this.careRepository = careRepository;
        this.articleRepository = articleRepository;
        this.experiencedGardenerRepository = gardenRepository;
        this.demandingPlantRepository = demandingPlantRepository;
    }


    public CareBO getCareById(Long id){
        CareEntity careEntity = careRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No care found, for id = " + id ));

        return entityToBusinessObject(careEntity);
    }

    public List<CareBO> getAllCares(){
        List<CareEntity> careEntityList = careRepository.findAll();

        if (careEntityList.isEmpty())
            throw new ResourceNotFoundException("No cares found");

        return careEntityList.stream().map(this::entityToBusinessObject).collect(Collectors.toList());
    }

    public CareBO saveCare(CareBO careBO){

        ArticleEntity articleEntity = articleRepository
                .findById(careBO.getArticleEntity().getId())
                .orElseThrow(() -> new  BusinessException("No such article found"));

        ExperiencedGardenerEntity experiencedGardenerEntity = experiencedGardenerRepository
                .findById(careBO.getExperiencedGardenerEntity().getId())
                .orElseThrow(() -> new  BusinessException("No such experienced gardener found"));

        DemandingPlantEntity demandingPlantEntity = demandingPlantRepository
                .findById(careBO.getDemandingPlantEntity().getId())
                .orElseThrow(() -> new  BusinessException("No such demanding plant found"));

        careBO.setArticleEntity(articleEntity);
        careBO.setExperiencedGardenerEntity(experiencedGardenerEntity);
        careBO.setDemandingPlantEntity(careBO.getDemandingPlantEntity());

        CareEntity careEntity = careRepository.saveAndFlush(businessObjectToEntity(careBO));
        LOGGER.info("Saved new care, as care entity = " + careEntity);

        articleEntity.setCareEntity(careEntity);
        articleRepository.saveAndFlush(articleEntity);
        LOGGER.info("Added new care to article entity = " + articleEntity);

        experiencedGardenerEntity.getCareEntity().add(careEntity);
        experiencedGardenerRepository.saveAndFlush(experiencedGardenerEntity);
        LOGGER.info("Added new care to experienced gardener entity = " + experiencedGardenerEntity);

        demandingPlantEntity.setCareEntity(careEntity);
        demandingPlantRepository.saveAndFlush(demandingPlantEntity);
        LOGGER.info("Added new care to demanding plant entity = " + demandingPlantEntity);

        return entityToBusinessObject(careEntity);
    }

    private CareEntity businessObjectToEntity(CareBO careBO){
        return CareEntity.builder()
                .startDate(careBO.getStartDate())
                .endDate(careBO.getEndDate())
                .articleEntity(careBO.getArticleEntity())
                .experiencedGardenerEntity(careBO.getExperiencedGardenerEntity())
                .demandingPlantEntity(careBO.getDemandingPlantEntity())
                .build();
    }

    @SuppressWarnings("all")
    private CareBO entityToBusinessObject(CareEntity careEntity){
        return CareBO.builder()
                .id(careEntity.getId())
                .startDate(careEntity.getStartDate())
                .endDate(careEntity.getEndDate())
                .articleEntity(careEntity.getArticleEntity())
                .experiencedGardenerEntity(careEntity.getExperiencedGardenerEntity())
                .demandingPlantEntity(careEntity.getDemandingPlantEntity())
                .build();
    }
}
