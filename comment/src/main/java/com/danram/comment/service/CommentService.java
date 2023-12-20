package com.danram.comment.service;

import com.danram.comment.dto.request.CommentAddRequestDto;
import com.danram.comment.dto.response.CommentAddResponseDto;

public interface CommentService {
    public CommentAddResponseDto addComment(CommentAddRequestDto dto);
}
