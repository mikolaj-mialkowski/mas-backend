package pjatk.mas_backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pjatk.mas_backend.models.business.GardenBO;
import pjatk.mas_backend.models.business.SpeciesBO;
import pjatk.mas_backend.services.GardenService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("${mas.backend.detailPath.gardenController}")
@Validated
public class GardenController {

    private final GardenService gardenService;

    public GardenController(GardenService gardenService) {
        this.gardenService = gardenService;
    }

    @GetMapping("/base")
    public ResponseEntity<GardenBO> getPrivateClients(){
        GardenBO gardenBO = gardenService.getGarden();
        return ResponseEntity.ok(gardenBO);
    }

    @PostMapping("/change")
    public ResponseEntity<GardenBO> addPrivateClient(@RequestBody @Valid GardenBO gardenBO){
        GardenBO changedGarden = gardenService.changeGarden(gardenBO);
        return ResponseEntity.ok(changedGarden);
    }

}
