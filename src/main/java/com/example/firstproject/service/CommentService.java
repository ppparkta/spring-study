package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
        return commentRepository.findByArticleId(articleId)
                .stream().map(comment -> CommentDto.createDto(comment))
                .collect(Collectors.toList());
//        List<Comment> comments = commentRepository.findByArticleId(articleId);
//        List<CommentDto> commentDtos = new ArrayList<CommentDto>();
//        for(int i=0;i<comments.size();i++){
//            Comment tmp = comments.get(i);
//            CommentDto tmpDto = CommentDto.createDto(tmp);
//            commentDtos.add(tmpDto);
//        }
//        return commentDtos;
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto commentDto) {
        //db에서 부모 게시글 가져오고 없으면 예외처리
        Article article = articleRepository.findById(articleId).orElseThrow(()->new IllegalArgumentException("댓글 생성 실패 > 대상 게시글이 없습니다."));
        log.info("comment dto="+commentDto.toString());
        //부모 게시글의 새 댓글 엔티티 생성
        Comment comment = Comment.create(commentDto, article);
        //생성된 엔티티 db에 저장
        Comment created = commentRepository.save(comment);
        //db에 저장한 엔티티 dto로 변환해서 반환하기
        return CommentDto.createDto(created);
    }
}
