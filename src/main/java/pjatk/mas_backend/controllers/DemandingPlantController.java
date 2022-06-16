package pjatk.mas_backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pjatk.mas_backend.models.business.DemandingPlantBO;
import pjatk.mas_backend.models.business.UndemandingPlantBO;
import pjatk.mas_backend.services.DemandingPlantService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("${mas.backend.detailPath.demandingPlantController}")
@Validated
public class DemandingPlantController {

    private final DemandingPlantService demandingPlantService;

    public DemandingPlantController(DemandingPlantService demandingPlantService) {
        this.demandingPlantService = demandingPlantService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<DemandingPlantBO>> getPrivateClients(){
        List<DemandingPlantBO> demandingPlantBOList = demandingPlantService.getAllDemandingPlants();
        return ResponseEntity.ok(demandingPlantBOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DemandingPlantBO> getPrivateClient(@PathVariable Long id){
        DemandingPlantBO demandingPlantBO = demandingPlantService.getDemandingPlantById(id);
        return ResponseEntity.ok(demandingPlantBO);
    }

    @PostMapping("/add")
    public ResponseEntity<DemandingPlantBO> addPrivateClient(@RequestBody @Valid DemandingPlantBO demandingPlantBO){
        DemandingPlantBO savedDemandingPlant = demandingPlantService.saveDemandingPlant(demandingPlantBO);
        return ResponseEntity.ok(savedDemandingPlant);
    }

}
