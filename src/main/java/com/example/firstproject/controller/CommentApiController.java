package com.example.firstproject.controller;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;

    //comment get
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable("articleId")Long articleId){
        //service action
        List<CommentDto> comments = commentService.comments(articleId);
        //return result
        if (comments != null)
            return ResponseEntity.status(HttpStatus.OK).body(comments);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    //comment post
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable("articleId") Long articleId, @RequestBody CommentDto commentDto){
        //action service
        log.info(commentDto.toString());
        CommentDto dto = commentService.create(articleId, commentDto);
        //return
        if (dto != null)
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //comment update

    //comment delete

}
