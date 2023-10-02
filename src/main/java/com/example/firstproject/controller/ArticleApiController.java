package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.ArticleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ArticleApiController {
    @Autowired
    private ArticleService articleService;
//    @Autowired
//    private ArticleRepository articleRepository;

    //get
    @GetMapping("/api/articles")
    public List<Article> index(){
//        return articleRepository.findAll();
        return articleService.index();
    }
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
//        return articleRepository.findById(id).orElse(null);
        return articleService.show(id);
    }

    //post
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm articleForm){
        //Article article = articleForm.toEntity();
        Article article = articleService.create(articleForm);
        //return articleRepository.save(article);
        return (article == null)? ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null) : ResponseEntity.status(HttpStatus.OK).body(article) ;
    }

    //patch
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm articleForm){
        Article article = articleService.update(id, articleForm);
        return (article != null)? ResponseEntity.status(HttpStatus.OK).body(article):ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    //delete
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        Article article = articleService.delete(id);
        return (article != null)?ResponseEntity.status(HttpStatus.NO_CONTENT).body(null):ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos){
        List <Article> createdList = articleService.createArticles(dtos);
        return createdList != null?ResponseEntity.status(HttpStatus.OK).body(createdList):ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
