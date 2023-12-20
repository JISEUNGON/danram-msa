package com.danram.comment.controller;

import com.danram.comment.dto.request.CommentAddRequestDto;
import com.danram.comment.dto.response.CommentAddResponseDto;
import com.danram.comment.dto.response.CommentAllResponseDto;
import com.danram.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comment")
@Slf4j
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/add")
    public ResponseEntity<CommentAddResponseDto> addComment(@RequestBody CommentAddRequestDto dto) {
        return ResponseEntity.ok(commentService.addComment(dto));
    }

    //edit
    //delete
    //like
    //unlike

    //free
    @GetMapping("/all")
    public ResponseEntity<CommentAllResponseDto> getAllComment() {
        return null;
        //return ResponseEntity.ok(commentService.findAll());
    }
}
