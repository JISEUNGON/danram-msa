package com.danram.comment.controller;

import com.danram.comment.dto.request.CommentAddRequestDto;
import com.danram.comment.dto.request.CommentEditRequestDto;
import com.danram.comment.dto.response.CommentAddResponseDto;
import com.danram.comment.dto.response.CommentAllResponseDto;
import com.danram.comment.dto.response.CommentEditResponseDto;
import com.danram.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/edit")
    public ResponseEntity<CommentEditResponseDto> editComment(@RequestBody CommentEditRequestDto dto) {
        return ResponseEntity.ok(commentService.editComment(dto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteComment(@RequestParam Long commentId) {
        return ResponseEntity.ok(commentService.deleteComment(commentId));
    }

    @GetMapping("/like")
    public ResponseEntity<Long> likeComment(@RequestParam Long commentId) {
        return ResponseEntity.ok(commentService.likeComment(commentId));
    }

    @GetMapping("/unlike")
    public ResponseEntity<Long> unlikeComment(@RequestParam Long commentId) {
        return ResponseEntity.ok(commentService.unlikeComment(commentId));
    }

    //free
    @GetMapping("/all")
    public ResponseEntity<List<CommentAllResponseDto>> getAllComment(@RequestParam Long feedId) {
        return ResponseEntity.ok(commentService.findAll(feedId));
    }
}
