package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
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
    private ArticleRepository articleRepository;

    //get
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleRepository.findAll();
    }
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleRepository.findById(id).orElse(null);
    }

    //post
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm articleForm){
        Article article = articleForm.toEntity();
        return articleRepository.save(article);
    }

    //patch
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm articleForm){
        //수정용 엔티티 생성
        Article article = articleForm.toEntity();
        //db에서 대상 엔티티 조회
        Article ori = articleRepository.findById(id).orElse(null);
        //대상 엔티티 잘못된 경우 예외처리
        if (ori == null || article.getId() != ori.getId())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        //대상 엔티티 있으면 수정 내용 업데이트 후 정상응답 보내기
        Article new_thing = articleRepository.save(article);
        return ResponseEntity.status(HttpStatus.OK).body(new_thing);
    }

    //delete
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        Article article = articleRepository.findById(id).orElse(null);
        if (article == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        articleRepository.delete(article);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
