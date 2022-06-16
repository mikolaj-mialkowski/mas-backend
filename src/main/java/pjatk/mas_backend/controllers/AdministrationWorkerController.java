package pjatk.mas_backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pjatk.mas_backend.models.business.AdministrationWorkerBO;
import pjatk.mas_backend.models.business.ExperiencedGardenerBO;
import pjatk.mas_backend.services.AdministrationWorkerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("${mas.backend.detailPath.administrationWorkerController}")
@Validated
public class AdministrationWorkerController {

    private final AdministrationWorkerService administrationWorkerService;

    public AdministrationWorkerController(AdministrationWorkerService administrationWorkerService) {
        this.administrationWorkerService = administrationWorkerService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<AdministrationWorkerBO>> getNoviceGardeners(){
        List<AdministrationWorkerBO> administrationWorkerBOList = administrationWorkerService.getAllAdministrationWorkers();
        return ResponseEntity.ok(administrationWorkerBOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministrationWorkerBO> getNoviceGardener(@PathVariable Long id){
        AdministrationWorkerBO administrationWorkerBO = administrationWorkerService.getAdministrationWorkerById(id);
        return ResponseEntity.ok(administrationWorkerBO);
    }

    @PostMapping("/add")
    public ResponseEntity<AdministrationWorkerBO> addNoviceGardener(@RequestBody @Valid AdministrationWorkerBO administrationWorkerBO){
        AdministrationWorkerBO savedAdministrationWorker = administrationWorkerService.saveAdministrationWorker(administrationWorkerBO);
        return ResponseEntity.ok(savedAdministrationWorker);
    }

}
