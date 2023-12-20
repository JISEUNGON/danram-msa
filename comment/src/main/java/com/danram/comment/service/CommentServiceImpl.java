package com.danram.comment.service;

import com.danram.comment.domain.Comment;
import com.danram.comment.dto.request.CommentAddRequestDto;
import com.danram.comment.dto.response.CommentAddResponseDto;
import com.danram.comment.repository.CommentLikeRepository;
import com.danram.comment.repository.CommentRepository;
import com.danram.comment.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import static com.danram.comment.config.MapperConfig.modelMapper;

@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;

    @Override
    @Transactional
    public CommentAddResponseDto addComment(final CommentAddRequestDto dto) {
        CommentAddResponseDto map = modelMapper.map(
                commentRepository.save(
                        Comment.builder()
                                .feedId(dto.getFeedId())
                                .parentId(dto.getParentId())
                                .content(dto.getContent())
                                .memberEmail(JwtUtil.getEmail())
                                .memberId(JwtUtil.getMemberId())
                                .deleted(false)
                                .build()
                ), CommentAddResponseDto.class
        );

        map.setLikeNum(0L);

        return map;
    }
}
