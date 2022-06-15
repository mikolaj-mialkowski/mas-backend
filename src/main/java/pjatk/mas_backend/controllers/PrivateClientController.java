package pjatk.mas_backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pjatk.mas_backend.models.business.PrivateClientBO;
import pjatk.mas_backend.services.PrivateClientService;

import javax.validation.Valid;
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
    public ResponseEntity<List<PrivateClientBO>> getPrivateClients(){
        List<PrivateClientBO> privateClients = privateClientService.getAllPrivateClients();

        return ResponseEntity.ok(privateClients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrivateClientBO> getPrivateClient(@PathVariable Long id){
        PrivateClientBO privateClient = privateClientService.getPrivateClientById(id);

        return ResponseEntity.ok(privateClient);
    }

    @PostMapping("/add")
    public ResponseEntity<PrivateClientBO> addPrivateClient(@RequestBody @Valid PrivateClientBO privateClientBO){
        PrivateClientBO privateClient = privateClientService.savePrivateClient(privateClientBO);

        return ResponseEntity.ok(privateClient);
    }

}
