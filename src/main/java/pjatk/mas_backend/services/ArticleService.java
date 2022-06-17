package pjatk.mas_backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pjatk.mas_backend.models.business.ArticleBO;
import pjatk.mas_backend.models.business.VisitBO;
import pjatk.mas_backend.models.entities.*;
import pjatk.mas_backend.models.exceptions.BusinessException;
import pjatk.mas_backend.repositories.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ArticleService.class);

    //private final VisitRepository visitRepository; //CARE

    private final ArticleRepository articleRepository;
    private final GardenRepository gardenRepository;

    private final AdministrationWorkerRepository administrationWorkerRepository;

    public ArticleService(ArticleRepository articleRepository, GardenRepository gardenRepository, AdministrationWorkerRepository administrationWorkerRepository) {
        this.articleRepository = articleRepository;
        this.gardenRepository = gardenRepository;
        this.administrationWorkerRepository = administrationWorkerRepository;
    }


    public ArticleBO getArticleById(Long id){
        ArticleEntity articleEntity = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No article found, for id = " + id ));

        return entityToBusinessObject(articleEntity);
    }

    public List<ArticleBO> getAllArticles(){
        List<ArticleEntity> articleEntityList = articleRepository.findAll();

        if (articleEntityList.isEmpty())
            throw new ResourceNotFoundException("No articles found");

        return articleEntityList.stream().map(this::entityToBusinessObject).collect(Collectors.toList());
    }

    public ArticleBO saveArticle(ArticleBO articleBO){

        GardenEntity gardenEntity = gardenRepository
                .findById(articleBO.getGardenEntity().getId())
                .orElseThrow(() -> new  BusinessException("No such garden found"));

        AdministrationWorkerEntity administrationWorkerEntity = administrationWorkerRepository
                .findById(articleBO.getAdministrationWorkerEntity().getId())
                .orElseThrow(() -> new  BusinessException("No such administration worker found"));

        articleBO.setGardenEntity(gardenEntity);
        articleBO.setAdministrationWorkerEntity(administrationWorkerEntity);

        ArticleEntity articleEntity = articleRepository.saveAndFlush(businessObjectToEntity(articleBO));
        LOGGER.info("Saved new article, as article entity = " + articleEntity);

        gardenEntity.getArticleEntities().add(articleEntity);
        gardenRepository.saveAndFlush(gardenEntity);
        LOGGER.info("Added new article to garden entity = " + gardenEntity);

        administrationWorkerEntity.getArticleEntities().add(articleEntity);
        administrationWorkerRepository.saveAndFlush(administrationWorkerEntity);
        LOGGER.info("Added new article to client entity = " + administrationWorkerEntity);

        return entityToBusinessObject(articleEntity);
    }

    private ArticleEntity businessObjectToEntity(ArticleBO articleBO){
        return ArticleEntity.builder()
                .name(articleBO.getName())
                .price(articleBO.getPrice())
                .destinationInfo(articleBO.getDestinationInfo())
                .gardenEntity(articleBO.getGardenEntity())
                .administrationWorkerEntity(articleBO.getAdministrationWorkerEntity())
                //CARE
                .build();
    }

    @SuppressWarnings("all")
    private ArticleBO entityToBusinessObject(ArticleEntity articleEntity){
        return ArticleBO.builder()
                .id(articleEntity.getId())
                .name(articleEntity.getName())
                .price(articleEntity.getPrice())
                .destinationInfo(articleEntity.getDestinationInfo())
                .gardenEntity(articleEntity.getGardenEntity())
                .administrationWorkerEntity(articleEntity.getAdministrationWorkerEntity())
                .build();
    }
}
