package com.danram.user.service.member;

import com.danram.user.domain.Authority;
import com.danram.user.domain.Member;
import com.danram.user.dto.request.login.OauthLoginRequestDto;
import com.danram.user.dto.response.login.LoginResponseDto;
import com.danram.user.exception.member.MemberIdNotFoundException;
import com.danram.user.repository.MemberRepository;
import com.danram.user.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.danram.user.config.MapperConfig.modelMapper;

@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Optional<Member> checkDuplicatedEmail(final String email) {
        return memberRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public LoginResponseDto signUp(final OauthLoginRequestDto dto) {
        Long memberId = System.currentTimeMillis();

        Member member = Member.builder()
                .memberId(memberId)
                .loginType(dto.getLoginType())
                .img(dto.getProfileImg())
                .ban(false)
                .pro(false)
                .nickname(dto.getNickname())
                .email(dto.getEmail())
                .signOut(false)
                .accessToken(JwtUtil.createJwt(memberId))
                .accessTokenExpiredAt(LocalDate.now().plusYears(1))
                .refreshToken(JwtUtil.createRefreshToken(memberId))
                .refreshTokenExpiredAt(LocalDate.now().plusYears(1))
                .authorities(List.of(
                        Authority.builder()
                                .authorityName("ROLE_ADMIN")
                                .build()
                        , Authority.builder()
                                .authorityName("ROLE_USER")
                                .build()
                ))
                .createdAt(LocalDateTime.now())
                .build();

        final Member save = memberRepository.save(member);

        return modelMapper.map(save, LoginResponseDto.class);
    }

    @Override
    @Transactional
    public LoginResponseDto signIn(final Member member) {
        Member member1 = memberRepository.findById(member.getMemberId()).orElseThrow(
                () -> new MemberIdNotFoundException(member.getMemberId())
        );

        return modelMapper.map(member1, LoginResponseDto.class);
    }

    @Override
    public String verifyToken(final String token) {
        final Optional<Member> byAccessToken = memberRepository.findByAccessToken(token);

        if(byAccessToken.isEmpty()) {
            return null;
        }
        else
        {
            Member member = byAccessToken.get();

            return member.getRole();
        }
    }
}
