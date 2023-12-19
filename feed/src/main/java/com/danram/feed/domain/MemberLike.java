package com.danram.feed.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MemberLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id", columnDefinition = "int")
    private Long likeId;

    @Column(name = "member_id", columnDefinition = "bigint")
    private Long memberId;

    @Column(name = "member_email", columnDefinition = "varchar", length = 50)
    private String memberEmail;

    @Column(name = "id", columnDefinition = "int")
    private Long id;

    @Column(name = "created_at", columnDefinition = "date")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "deleted", columnDefinition = "tinyint")
    private Boolean deleted;

    @Column(name = "type", columnDefinition = "int")
    private Long type;

    @Column(name = "description", columnDefinition = "varchar", length = 200)
    private String description;
}
