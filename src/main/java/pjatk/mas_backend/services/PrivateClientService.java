package pjatk.mas_backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pjatk.mas_backend.models.business.PrivateClientBO;
import pjatk.mas_backend.models.entities.ClientEntity;
import pjatk.mas_backend.models.enums.ClientType;
import pjatk.mas_backend.repositories.ClientRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrivateClientService {

    private final static Logger LOGGER = LoggerFactory.getLogger(PrivateClientService.class);

    private final ClientRepository clientRepository;

    public PrivateClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public PrivateClientBO getPrivateClientById(Long id){
        ClientEntity privateClientEntity = clientRepository.findByClientTypeAndId(ClientType.PRIVATE,id);

        if (privateClientEntity == null)
            throw new ResourceNotFoundException("No private client found, for id = "+id);

        return entityToBusinessObject(privateClientEntity);
    }

    public List<PrivateClientBO> getAllPrivateClients(){
        List<ClientEntity> privateClientEntityList = clientRepository.findAllByClientType(ClientType.PRIVATE);

        if (privateClientEntityList == null || privateClientEntityList.isEmpty())
            throw new ResourceNotFoundException("No private clients found");

        return privateClientEntityList.stream().map(this::entityToBusinessObject).collect(Collectors.toList());
    }

    public PrivateClientBO savePrivateClient(PrivateClientBO privateClientBO){
        ClientEntity clientEntity = businessObjectToEntity(privateClientBO);
        ClientEntity savedEntity = clientRepository.saveAndFlush(clientEntity);
        LOGGER.debug("clientEntity = " + clientEntity);
        LOGGER.debug("savedEntity = " + clientEntity);
        LOGGER.debug(String.valueOf(clientEntity.equals(savedEntity)));

        return entityToBusinessObject(savedEntity);
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

    private PrivateClientBO entityToBusinessObject(ClientEntity clientEntity){
        return PrivateClientBO.builder()
                .id(clientEntity.getId())
                .firstName(clientEntity.getFirstName())
                .lastName(clientEntity.getLastName())
                .phoneNumber(clientEntity.getPhoneNumber())
                .accessToGreenhouse(clientEntity.getAccessToGreenhouse())
                .build();
    }

}
