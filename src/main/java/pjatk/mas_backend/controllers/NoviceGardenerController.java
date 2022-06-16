package pjatk.mas_backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pjatk.mas_backend.models.business.NoviceGardenerBO;
import pjatk.mas_backend.models.business.PrivateClientBO;
import pjatk.mas_backend.services.NoviceGardenerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("${mas.backend.detailPath.noviceGardenerController}")
@Validated
public class NoviceGardenerController {

    private final NoviceGardenerService noviceGardenerService;

    public NoviceGardenerController(NoviceGardenerService noviceGardenerService) {
        this.noviceGardenerService = noviceGardenerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<NoviceGardenerBO>> getNoviceGardeners(){
        List<NoviceGardenerBO> noviceGardenerBOList = noviceGardenerService.getAllNoviceGardeners();
        return ResponseEntity.ok(noviceGardenerBOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoviceGardenerBO> getNoviceGardener(@PathVariable Long id){
        NoviceGardenerBO noviceGardenerBO = noviceGardenerService.getNoviceGardenerById(id);
        return ResponseEntity.ok(noviceGardenerBO);
    }

    @PostMapping("/add")
    public ResponseEntity<NoviceGardenerBO> addNoviceGardener(@RequestBody @Valid NoviceGardenerBO noviceGardenerBO){
        NoviceGardenerBO savedNoviceGardener = noviceGardenerService.saveNoviceGardener(noviceGardenerBO);
        return ResponseEntity.ok(savedNoviceGardener);
    }

}
