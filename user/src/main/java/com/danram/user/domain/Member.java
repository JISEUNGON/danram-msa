package com.danram.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Member {
    @Id
    @Column(name = "member_id", columnDefinition = "bigint")
    public Long memberId;

    @Column(name = "nickname", columnDefinition = "varchar", length = 30)
    private String nickname;

    @Column(name = "email", columnDefinition = "varchar", length = 50)
    private String email;

    @Column(name = "login_type", columnDefinition = "int")
    private Long loginType; //naver, kakako, apple

    @Column(name = "img", columnDefinition = "text")
    private String img;

    @Column(name = "pro", columnDefinition = "tinyint")
    private Boolean pro;

    @Column(name = "ban", columnDefinition = "tinyint")
    private Boolean ban;

    @Column(name = "sign_out", columnDefinition = "tinyint")
    private Boolean signOut;

    @Column(name = "access_token", columnDefinition = "text")
    private String accessToken;

    @Column(name = "access_token_expired_at", columnDefinition = "date")
    private LocalDate accessTokenExpiredAt;

    @Column(name = "refresh_token", columnDefinition = "text")
    private LocalDate refreshToken;

    @Column(name = "refresh_token_expired_at", columnDefinition = "date")
    private LocalDate refreshTokenExpiredAt;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
