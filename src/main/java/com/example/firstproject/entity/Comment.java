package com.example.firstproject.entity;

import com.example.firstproject.dto.CommentDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String body;
    @ManyToOne
    @JoinColumn(name="article_id")
    private Article article;

    public static Comment create(CommentDto commentDto, Article article) {
        //exception
        if (commentDto.getId() != null)
            throw new IllegalArgumentException("댓글의 id가 없어야 합니다.");
        if (commentDto.getArticleId() != article.getId())
            throw new IllegalArgumentException("게시글의 id가 잘못됐습니다.");
        //entity create and return
        return new Comment(commentDto.getId(), commentDto.getNickname(), commentDto.getBody(), article);
    }
}
