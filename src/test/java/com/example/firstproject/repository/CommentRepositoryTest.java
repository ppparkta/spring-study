package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;
    @Test
    @DisplayName("1번 아티클 댓글 조회")
    void findByArticleId() {
        //입력데이터준비
        Long articleId = 1L;

        //실제데이터
        List<Comment> commentList = commentRepository.findByArticleId(articleId);
        //예상데이터
        Article article = new Article(1L, "test1","content1");
        Comment a = new Comment(1L, "sooyang", "으으으음 테스트 코멘트", article);
        Comment b = new Comment(2L, "dohun", "으으으음 테스트 코멘트맞는디...", article);
        Comment c = new Comment(3L, "hoony", "찐 테스트", article);
        List<Comment> expected = Arrays.asList(a,b,c);
        //비교및검증
        assertEquals(expected.toString(),commentList.toString(), "1번 글의 모든 댓글 출력");
    }

    @Test
    @DisplayName("3번 article 댓글 조회")
    void findByArticleIdThree() {
        //입력데이터준비
        Long articleId = 3L;

        //실제데이터
        List<Comment> commentList = commentRepository.findByArticleId(articleId);
        //예상데이터
        Article article = new Article(3L, "test3","content3");
        List<Comment> expected = Arrays.asList();
        //비교및검증
        assertEquals(expected.toString(),commentList.toString(), "3번 글의 모든 댓글 출력");
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        //입력데이터준비
        String nickname = "sooyang";
        Article article = new Article(1L, "test1", "content1");
        //실제데이터
        List<Comment> commentList = commentRepository.findByNickname("sooyang");
        //예상데이터
        Comment comment = new Comment(1L, "sooyang", "으으으음 테스트 코멘트", article);
        List<Comment> expected = new ArrayList<>();
        expected.add(comment);
        //비교및검증
        assertEquals(commentList.toString(), expected.toString(), "sooyang의 모든 댓글 조회");
    }
}