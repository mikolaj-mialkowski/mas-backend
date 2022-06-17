package pjatk.mas_backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pjatk.mas_backend.models.business.SpeciesBO;
import pjatk.mas_backend.models.business.UndemandingPlantBO;
import pjatk.mas_backend.services.UndemandingPlantService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("${mas.backend.detailPath.undemandingPlantController}")
@Validated
public class UndemandingPlantController {

    private final UndemandingPlantService undemandingPlantService;

    public UndemandingPlantController(UndemandingPlantService undemandingPlantService) {
        this.undemandingPlantService = undemandingPlantService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UndemandingPlantBO>> getUndemandingPlants(){
        List<UndemandingPlantBO> undemandingPlantBOList = undemandingPlantService.getAllUndemandingPlants();
        return ResponseEntity.ok(undemandingPlantBOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UndemandingPlantBO> getUndemandingPlant(@PathVariable Long id){
        UndemandingPlantBO undemandingPlantBO = undemandingPlantService.getUndemandingPlantById(id);
        return ResponseEntity.ok(undemandingPlantBO);
    }

    @PostMapping("/add")
    public ResponseEntity<UndemandingPlantBO> addUndemandingPlant(@RequestBody @Valid UndemandingPlantBO undemandingPlantBO){
        UndemandingPlantBO savedUndemandingPlant = undemandingPlantService.saveUndemandingPlant(undemandingPlantBO);
        return ResponseEntity.ok(savedUndemandingPlant);
    }

}
