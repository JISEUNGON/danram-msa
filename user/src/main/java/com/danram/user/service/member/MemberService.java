package com.danram.user.service.member;

import com.danram.user.domain.Member;
import com.danram.user.dto.request.login.OauthLoginRequestDto;
import com.danram.user.dto.response.login.LoginResponseDto;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    public List<Member> findAll();
    public Optional<Member> checkDuplicatedEmail(String email);
    public LoginResponseDto signUp(OauthLoginRequestDto dto);
    public LoginResponseDto signIn(Member member);
}
