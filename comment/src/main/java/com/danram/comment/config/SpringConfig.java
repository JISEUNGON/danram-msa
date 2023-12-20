package com.danram.comment.config;

import com.danram.comment.repository.CommentLikeRepository;
import com.danram.comment.repository.CommentRepository;
import com.danram.comment.service.CommentService;
import com.danram.comment.service.CommentServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;

    public SpringConfig(final CommentRepository commentRepository,
                        final CommentLikeRepository commentLikeRepository) {
        this.commentRepository = commentRepository;
        this.commentLikeRepository = commentLikeRepository;
    }

    @Bean
    public CommentService commentService() {
        return new CommentServiceImpl(commentRepository, commentLikeRepository);
    }
}
