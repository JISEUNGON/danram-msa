package com.danram.comment.service;

import com.danram.comment.dto.request.CommentAddRequestDto;
import com.danram.comment.dto.request.CommentEditRequestDto;
import com.danram.comment.dto.response.CommentAddResponseDto;
import com.danram.comment.dto.response.CommentAllResponseDto;
import com.danram.comment.dto.response.CommentEditResponseDto;

import java.util.List;

public interface CommentService {
    public CommentAddResponseDto addComment(CommentAddRequestDto dto);
    public CommentEditResponseDto editComment(CommentEditRequestDto dto);
    public String deleteComment(Long commentId);
    public Long likeComment(Long commentId);
    public Long unlikeComment(Long commentId);
    public List<CommentAllResponseDto> findAll(Long feedId);
}
