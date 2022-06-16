package pjatk.mas_backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pjatk.mas_backend.models.business.VisitBO;
import pjatk.mas_backend.models.entities.ClientEntity;
import pjatk.mas_backend.models.entities.GardenEntity;
import pjatk.mas_backend.models.entities.VisitEntity;
import pjatk.mas_backend.models.exceptions.BusinessException;
import pjatk.mas_backend.repositories.ClientRepository;
import pjatk.mas_backend.repositories.GardenRepository;
import pjatk.mas_backend.repositories.VisitRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitService {

    private final static Logger LOGGER = LoggerFactory.getLogger(VisitService.class);

    private final VisitRepository visitRepository;

    private final GardenRepository gardenRepository;

    private final ClientRepository clientRepository;

    public VisitService(VisitRepository visitRepository, GardenRepository gardenRepository, ClientRepository clientRepository) {
        this.visitRepository = visitRepository;
        this.gardenRepository = gardenRepository;
        this.clientRepository = clientRepository;
    }

    public VisitBO getVisitById(Long id){
        VisitEntity visitEntity = visitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No visit found, for id = " + id ));

        return entityToBusinessObject(visitEntity);
    }

    public List<VisitBO> getAllVisits(){
        List<VisitEntity> visitEntityList = visitRepository.findAll();

        if (visitEntityList.isEmpty())
            throw new ResourceNotFoundException("No visits found");

        return visitEntityList.stream().map(this::entityToBusinessObject).collect(Collectors.toList());
    }

    public VisitBO saveVisit(VisitBO visitBO){

        GardenEntity gardenEntity = gardenRepository
                .findById(visitBO.getGardenEntity().getId())
                .orElseThrow(() -> new  BusinessException("No such garden found"));

        ClientEntity clientEntity = clientRepository
                .findById(visitBO.getClientEntity().getId())
                .orElseThrow(() -> new  BusinessException("No such client found"));

        visitBO.setGardenEntity(gardenEntity);
        visitBO.setClientEntity(clientEntity);

        VisitEntity visitEntity = visitRepository.saveAndFlush(businessObjectToEntity(visitBO));
        LOGGER.info("Saved new visit, as visit entity = " + visitEntity);

        gardenEntity.getVisitEntity().add(visitEntity);
        gardenRepository.saveAndFlush(gardenEntity);
        LOGGER.info("Added new visit to garden entity = " + gardenEntity);

        clientEntity.getVisitEntity().add(visitEntity);
        clientRepository.saveAndFlush(clientEntity);
        LOGGER.info("Added new visit to client entity = " + clientEntity);

        return entityToBusinessObject(visitEntity);
    }

    private VisitEntity businessObjectToEntity(VisitBO visitBO){
        return VisitEntity.builder()
                .date(visitBO.getDate())
                .gardenEntity(visitBO.getGardenEntity())
                .clientEntity(visitBO.getClientEntity())
                .build();
    }

    @SuppressWarnings("all")
    private VisitBO entityToBusinessObject(VisitEntity visitEntity){
        return VisitBO.builder()
                .id(visitEntity.getId())
                .date(visitEntity.getDate())
                .clientEntity(visitEntity.getClientEntity())
                .gardenEntity(visitEntity.getGardenEntity())
                .build();
    }
}
