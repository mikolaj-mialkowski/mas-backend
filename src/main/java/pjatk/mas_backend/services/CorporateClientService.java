package pjatk.mas_backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pjatk.mas_backend.models.business.CorporateClientBO;
import pjatk.mas_backend.models.entities.ClientEntity;
import pjatk.mas_backend.models.enums.ClientType;
import pjatk.mas_backend.repositories.ClientRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CorporateClientService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CorporateClientService.class);

    private final ClientRepository clientRepository;

    public CorporateClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public CorporateClientBO getCorporateClientById(Long id){
        ClientEntity corporateClientEntity = clientRepository.findByClientTypeAndId(ClientType.CORPORATE,id);

        if (corporateClientEntity == null)
            throw new ResourceNotFoundException("No corporate client found, for id = "+id);

        return entityToBusinessObject(corporateClientEntity);
    }

    public List<CorporateClientBO> getAllCorporateClients(){
        List<ClientEntity> corporateClientEntityList = clientRepository.findAllByClientType(ClientType.CORPORATE);

        if (corporateClientEntityList == null || corporateClientEntityList.isEmpty())
            throw new ResourceNotFoundException("No corporate clients found");

        return corporateClientEntityList.stream().map(this::entityToBusinessObject).collect(Collectors.toList());
    }

    public CorporateClientBO saveCorporateClient(CorporateClientBO corporateClientBO){
        ClientEntity savedEntity = clientRepository.saveAndFlush(businessObjectToEntity(corporateClientBO));
        LOGGER.info("Saved new corporate client, as client entity = " + savedEntity);
        return entityToBusinessObject(savedEntity);
    }

    private ClientEntity businessObjectToEntity(CorporateClientBO corporateClientBO){
        return ClientEntity.builder()
                .clientType(ClientType.CORPORATE)
                .firstName(corporateClientBO.getFirstName())
                .lastName(corporateClientBO.getLastName())
                .companyName(corporateClientBO.getCompanyName())
                .discountAmount(corporateClientBO.getDiscountAmount())
                .build();
    }

    @SuppressWarnings("all")
    private CorporateClientBO entityToBusinessObject(ClientEntity clientEntity){
        return CorporateClientBO.builder()
                .id(clientEntity.getId())
                .firstName(clientEntity.getFirstName())
                .lastName(clientEntity.getLastName())
                .companyName(clientEntity.getCompanyName())
                .discountAmount(clientEntity.getDiscountAmount())
                .visitEntities(clientEntity.getVisitEntities())
                .clientType(clientEntity.getClientType())
                .build();
    }

}
