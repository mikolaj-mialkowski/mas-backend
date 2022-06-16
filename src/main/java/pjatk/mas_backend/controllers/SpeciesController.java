package pjatk.mas_backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pjatk.mas_backend.models.business.PrivateClientBO;
import pjatk.mas_backend.models.business.SpeciesBO;
import pjatk.mas_backend.services.SpeciesService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("${mas.backend.detailPath.speciesController}")
@Validated
public class SpeciesController {

    private final SpeciesService speciesService;

    public SpeciesController(SpeciesService speciesService) {
        this.speciesService = speciesService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<SpeciesBO>> getPrivateClients(){
        List<SpeciesBO> speciesBOList = speciesService.getAllSpecies();
        return ResponseEntity.ok(speciesBOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpeciesBO> getPrivateClient(@PathVariable Long id){
        SpeciesBO speciesBO = speciesService.getSpeciesById(id);
        return ResponseEntity.ok(speciesBO);
    }

    @PostMapping("/add")
    public ResponseEntity<SpeciesBO> addPrivateClient(@RequestBody @Valid SpeciesBO speciesBO){
        SpeciesBO savedSpecies = speciesService.saveSpecies(speciesBO);
        return ResponseEntity.ok(savedSpecies);
    }

}
