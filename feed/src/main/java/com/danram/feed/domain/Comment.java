package com.danram.feed.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", columnDefinition = "int")
    private Long commentId;

    @Column(name = "feed_id", columnDefinition = "int")
    private Long feedId;

    @Column(name = "member_id", columnDefinition = "bigint")
    private Long memberId;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Column(name = "parent_id", columnDefinition = "int")
    private Long parentId;

    @Column(name = "updated_at", columnDefinition = "datetime")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "deleted", columnDefinition = "tinyint")
    private Boolean deleted;
}
