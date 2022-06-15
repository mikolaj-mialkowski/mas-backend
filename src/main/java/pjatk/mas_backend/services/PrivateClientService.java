package pjatk.mas_backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pjatk.mas_backend.models.business.PrivateClientBO;
import pjatk.mas_backend.models.entities.ClientEntity;
import pjatk.mas_backend.models.enums.ClientType;
import pjatk.mas_backend.repositories.ClientRepository;
import pjatk.mas_backend.utils.DefaultExceptionHandler;

import java.util.List;

@Service
public class PrivateClientService {

    private final static Logger LOGGER = LoggerFactory.getLogger(PrivateClientService.class);

    private final ClientRepository clientRepository;

    public PrivateClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientEntity getPrivateClientById(Long id){
        ClientEntity privateClient = clientRepository.findByClientTypeAndId(ClientType.PRIVATE,id);

        if (privateClient == null)
            throw new ResourceNotFoundException("No private client found, for id="+id);

        return privateClient;
    }

    public List<ClientEntity> getAllPrivateClients(){
        List<ClientEntity> privateClients = clientRepository.findAllByClientType(ClientType.PRIVATE);

        if (privateClients == null || privateClients.isEmpty())
            throw new ResourceNotFoundException("No private clients found");

        return privateClients;
    }

    public PrivateClientBO savePrivateClient(PrivateClientBO privateClientBO){
        ClientEntity clientEntity = businessObjectToEntity(privateClientBO);
        ClientEntity savedEntity = clientRepository.saveAndFlush(clientEntity);
        LOGGER.debug("clientEntity = " + clientEntity);
        LOGGER.debug("savedEntity = " + clientEntity);
        LOGGER.debug(String.valueOf(clientEntity.equals(savedEntity)));

        return EntityToBusinessObject(savedEntity);
    }

    private ClientEntity businessObjectToEntity(PrivateClientBO privateClientBO){
        return ClientEntity.builder()
                        .clientType(ClientType.PRIVATE)
                        .firstName(privateClientBO.getFirstName())
                        .lastName(privateClientBO.getLastName())
                        .phoneNumber(privateClientBO.getPhoneNumber())
                        .accessToGreenhouse(Boolean.FALSE)
                        .build();
    }

    private PrivateClientBO EntityToBusinessObject(ClientEntity clientEntity){
        return PrivateClientBO.builder()
                .id(clientEntity.getId())
                .firstName(clientEntity.getFirstName())
                .lastName(clientEntity.getLastName())
                .phoneNumber(clientEntity.getPhoneNumber())
                .accessToGreenhouse(clientEntity.getAccessToGreenhouse())
                .build();
    }

}
