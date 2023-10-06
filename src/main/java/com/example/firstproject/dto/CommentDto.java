package com.example.firstproject.dto;

import com.example.firstproject.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.PrivateKey;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {
    private Long id;
    private String nickname;
    private String body;
    private Long articleId;

    public static CommentDto createDto(Comment tmp) {
        return new CommentDto(tmp.getId(), tmp.getNickname(), tmp.getBody(), tmp.getArticle().getId());
    }
}
