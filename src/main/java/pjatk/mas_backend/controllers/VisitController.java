package pjatk.mas_backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pjatk.mas_backend.models.business.SpeciesBO;
import pjatk.mas_backend.models.business.VisitBO;
import pjatk.mas_backend.services.VisitService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("${mas.backend.detailPath.visitController}")
@Validated
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<VisitBO>> getPrivateClients(){
        List<VisitBO> visitBOList = visitService.getAllVisits();
        return ResponseEntity.ok(visitBOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitBO> getPrivateClient(@PathVariable Long id){
        VisitBO visitBO = visitService.getVisitById(id);
        return ResponseEntity.ok(visitBO);
    }

    @PostMapping("/add")
    public ResponseEntity<VisitBO> addPrivateClient(@RequestBody @Valid VisitBO visitBO){
        VisitBO savedVisit = visitService.saveVisit(visitBO);
        return ResponseEntity.ok(savedVisit);
    }

}
