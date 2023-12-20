package com.danram.comment.repository;

import com.danram.comment.domain.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    public List<Comment> findByFeedIdAndDeletedFalse(Long feedId, Sort sort);
}
