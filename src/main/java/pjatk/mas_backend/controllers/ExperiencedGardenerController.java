package pjatk.mas_backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pjatk.mas_backend.models.business.ExperiencedGardenerBO;
import pjatk.mas_backend.services.ExperiencedGardenerService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("${mas.backend.detailPath.experiencedGardenerController}")
@Validated
public class ExperiencedGardenerController {

    private final ExperiencedGardenerService experiencedGardenerService;

    public ExperiencedGardenerController(ExperiencedGardenerService experiencedGardenerService) {
        this.experiencedGardenerService = experiencedGardenerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ExperiencedGardenerBO>> getExperiencedGardeners(){
        List<ExperiencedGardenerBO> experiencedGardenerBOList = experiencedGardenerService.getAllExperiencedGardeners();
        return ResponseEntity.ok(experiencedGardenerBOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExperiencedGardenerBO> getExperiencedGardener(@PathVariable Long id){
        ExperiencedGardenerBO experiencedGardenerBO = experiencedGardenerService.getExperiencedGardenerById(id);
        return ResponseEntity.ok(experiencedGardenerBO);
    }

    @PostMapping("/add")
    public ResponseEntity<ExperiencedGardenerBO> addExperiencedGardener(@RequestBody @Valid ExperiencedGardenerBO experiencedGardenerBO){
        ExperiencedGardenerBO savedExperiencedGardener = experiencedGardenerService.saveExperiencedGardener(experiencedGardenerBO);
        return ResponseEntity.ok(savedExperiencedGardener);
    }

}
