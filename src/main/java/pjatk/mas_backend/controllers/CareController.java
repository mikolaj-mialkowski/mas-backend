package pjatk.mas_backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pjatk.mas_backend.models.business.ArticleBO;
import pjatk.mas_backend.models.business.CareBO;
import pjatk.mas_backend.services.CareService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("${mas.backend.detailPath.careController}")
@Validated
public class CareController {

    private final CareService careService;

    public CareController(CareService careService) {
        this.careService = careService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CareBO>> getArticles(){
        List<CareBO> careBOList = careService.getAllCares();
        return ResponseEntity.ok(careBOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CareBO> getPrivateClient(@PathVariable Long id){
        CareBO careBO = careService.getCareById(id);
        return ResponseEntity.ok(careBO);
    }

    @PostMapping("/add")
    public ResponseEntity<CareBO> addPrivateClient(@RequestBody @Valid CareBO careBO){
        CareBO savedCare = careService.saveCare(careBO);
        return ResponseEntity.ok(savedCare);
    }

}
