package com.example.firstproject.repository;

import com.example.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //특정 게시글의 모든 댓글 조회 - 네이티브 쿼리 메서드
    @Query(value="select * from comment where article_id = :articleId", nativeQuery = true)
    List<Comment> findByArticleId(Long articleId);
    //특정 닉네임의 모든 댓글 조회 - 네이티브 쿼리 XML
    List<Comment> findByNickname(String nickname);

}
