package com.danram.user.repository;

import com.danram.user.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    public Optional<Member> findByEmail(String email);
    public Optional<Member> findByAccessTokenAndMemberId(String accessToken, Long memberId);
}
