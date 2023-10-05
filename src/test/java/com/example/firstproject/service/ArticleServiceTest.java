package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;
    @Test
    void index() {
        //예상 데이터
        Article a= new Article(1L, "test1", "content1");
        Article b= new Article(2L, "test2", "content2");
        Article c= new Article(3L, "test3", "content3");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));
        //실제 데이터
        List<Article> articles = articleService.index();
        //비교 및 검증
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_적절한_id() {
        //예상데이터
        Long id = 1L;
        Article expected = new Article(id, "test1", "content1");
        //실제데이터
        Article article = articleService.show(id);
        //비교및검증
        assertEquals(expected.toString(), article.toString());
    }
    @Test
    void show_부적절한_id() {
        //예상데이터
        Long id = -1L;
        Article expected = null;
        //실제데이터
        Article article = articleService.show(id);
        //비교및검증
        assertEquals(expected, article);
    }

    @Test
    void create_id_포함되지_않은_dto() {
        //예상데이터
        Article expected = new Article(4L, "test1", "content1");
        //실제데이터
        ArticleForm articleForm = new ArticleForm(null, "test1", "content1");
        Article article = articleService.create(articleForm);
        //비교및검증
        assertEquals(expected.toString(), article.toString());
    }
    @Test
    void create_id_포함된_dto() {
        //예상데이터
        Article expected = null;
        //실제데이터
        ArticleForm articleForm = new ArticleForm(4L, "test1", "content1");
        Article article = articleService.create(articleForm);
        //비교및검증
        assertEquals(expected, article);
    }

}