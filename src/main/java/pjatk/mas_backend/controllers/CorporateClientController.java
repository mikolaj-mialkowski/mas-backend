package pjatk.mas_backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pjatk.mas_backend.models.business.CorporateClientBO;
import pjatk.mas_backend.services.CorporateClientService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("${mas.backend.detailPath.corporateClientController}")
@Validated
public class CorporateClientController {

    private final CorporateClientService corporateClientService;

    public CorporateClientController(CorporateClientService corporateClientService) {
        this.corporateClientService = corporateClientService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CorporateClientBO>> getCorporateClients(){
        List<CorporateClientBO> corporateClientBOList = corporateClientService.getAllCorporateClients();
        return ResponseEntity.ok(corporateClientBOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CorporateClientBO> getCorporateClient(@PathVariable Long id){
        CorporateClientBO corporateClientBO = corporateClientService.getCorporateClientById(id);
        return ResponseEntity.ok(corporateClientBO);
    }

    @PostMapping("/add")
    public ResponseEntity<CorporateClientBO> addCorporateClient(@RequestBody @Valid CorporateClientBO corporateClientBO){
        CorporateClientBO savedClient  = corporateClientService.saveCorporateClient(corporateClientBO);
        return ResponseEntity.ok(savedClient);
    }

}
