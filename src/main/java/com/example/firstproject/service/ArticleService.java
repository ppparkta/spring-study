package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm articleForm) {
        Article article = articleForm.toEntity();
        if (article.getId() != null)
            return null;
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm articleForm) {
        //수정용 엔티티 생성
        Article article = articleForm.toEntity();
        //db에서 대상 엔티티 조회
        Article ori = articleRepository.findById(id).orElse(null);
        //대상 엔티티 잘못된 경우 예외처리
        if (ori == null || article.getId() != ori.getId())
            return null;
        //대상 엔티티 있으면 수정 내용 업데이트 후 정상응답 보내기
        return articleRepository.save(article);
    }

    public Article delete(Long id) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article == null)
            return null;
        articleRepository.delete(article);
        return article;
    }

//    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        //dto리스트->article리스트로 변환
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
        //article리스트 db에 저장
        articleList.stream()
                .forEach(article -> articleRepository.save(article));
        //강제로 에러 발생
        articleRepository.findById(-1L)
                .orElseThrow(()->new IllegalArgumentException("결제 실패"));
        //결과값 반환
        return articleList;
    }
}
