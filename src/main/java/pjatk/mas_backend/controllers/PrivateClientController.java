package pjatk.mas_backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pjatk.mas_backend.models.entities.ClientEntity;
import pjatk.mas_backend.services.PrivateClientService;

import java.util.List;

@RestController
@RequestMapping("${mas.backend.detailPath.privateClientController}")
@Validated
public class PrivateClientController {

    private final PrivateClientService privateClientService;


    public PrivateClientController(PrivateClientService privateClientService) {
        this.privateClientService = privateClientService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<ClientEntity>> getPrivateClients(){
        List<ClientEntity> privateClients = privateClientService.getAllPrivateClients();

        return ResponseEntity.ok(privateClients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientEntity> getPrivateClient(@PathVariable Long id){
        ClientEntity privateClient = privateClientService.getPrivateClientById(id);

        return ResponseEntity.ok(privateClient);
    }

}
