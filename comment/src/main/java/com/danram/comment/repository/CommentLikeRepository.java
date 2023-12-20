package com.danram.comment.repository;

import com.danram.comment.domain.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    public Optional<CommentLike> findByCommentIdAndMemberId(Long commentId, Long memberId);
    public List<Object> findByCommentIdAndDeletedFalse(Long commentId);
}
