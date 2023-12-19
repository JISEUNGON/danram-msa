package com.danram.feed.repository;

import com.danram.feed.domain.MemberLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberLikeRepository extends JpaRepository<MemberLike, Long> {
}
