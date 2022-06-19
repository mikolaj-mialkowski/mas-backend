package pjatk.mas_backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pjatk.mas_backend.models.business.ArticleBO;
import pjatk.mas_backend.models.business.VisitBO;
import pjatk.mas_backend.services.ArticleService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("${mas.backend.detailPath.articleController}")
@Validated
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ArticleBO>> getArticles(){
        List<ArticleBO> articleBOList = articleService.getAllArticles();
        return ResponseEntity.ok(articleBOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleBO> getArticle(@PathVariable Long id){
        ArticleBO articleBO = articleService.getArticleById(id);
        return ResponseEntity.ok(articleBO);
    }

    @PostMapping("/add")
    public ResponseEntity<ArticleBO> addArticle(@RequestBody @Valid ArticleBO articleBO){
        ArticleBO savedArticle = articleService.saveArticle(articleBO);
        return ResponseEntity.ok(savedArticle);
    }

}
