package pjatk.mas_backend.services;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pjatk.mas_backend.models.entities.ClientEntity;
import pjatk.mas_backend.models.enums.ClientType;
import pjatk.mas_backend.repositories.ClientRepository;

import java.util.List;

@Service
public class PrivateClientService {

    private final ClientRepository clientRepository;

    public PrivateClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientEntity getPrivateClientById(Long id){
        ClientEntity privateClient = clientRepository.findByClientTypeAndId(ClientType.PRIVATE,id);

        if (privateClient == null)
            throw new ResourceNotFoundException("No private client for id="+id);

        return privateClient;
    }

    public List<ClientEntity> getAllPrivateClients(){
        List<ClientEntity> privateClients = clientRepository.findAllByClientType(ClientType.PRIVATE);

        if (privateClients == null || privateClients.isEmpty())
            throw new ResourceNotFoundException("No private clients found");

        return privateClients;
    }

}
