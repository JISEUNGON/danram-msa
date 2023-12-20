package com.danram.feed.repository;

import com.danram.feed.domain.Feed;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<Feed, Long> {
    public Slice<Feed> findByDeletedAtIsNull(Pageable pageable);
}
