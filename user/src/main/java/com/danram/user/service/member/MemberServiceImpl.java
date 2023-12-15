package com.danram.user.service.member;

import com.danram.user.domain.Authority;
import com.danram.user.domain.DeletedMember;
import com.danram.user.domain.Member;
import com.danram.user.dto.request.login.OauthLoginRequestDto;
import com.danram.user.dto.request.member.MemberEditRequestDto;
import com.danram.user.dto.response.member.MemberAdminResponseDto;
import com.danram.user.dto.request.token.TokenReissueResponseDto;
import com.danram.user.dto.response.login.LoginResponseDto;
import com.danram.user.dto.response.member.MemberInfoResponseDto;
import com.danram.user.dto.response.member.MemberResponseDto;
import com.danram.user.dto.response.token.TokenResponseDto;
import com.danram.user.exception.member.MemberEmailNotFoundException;
import com.danram.user.exception.member.MemberIdNotFoundException;
import com.danram.user.exception.member.MemberLoginTypeNotExistException;
import com.danram.user.exception.member.MemberNotExistException;
import com.danram.user.repository.DeletedMemberRepository;
import com.danram.user.repository.MemberRepository;
import com.danram.user.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.danram.user.config.MapperConfig.modelMapper;

@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final DeletedMemberRepository deletedMemberRepository;

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
                .accessToken(JwtUtil.createJwt(memberId))
                .accessTokenExpiredAt(LocalDate.now().plusYears(1))
                .refreshToken(JwtUtil.createRefreshToken(memberId))
                .refreshTokenExpiredAt(LocalDate.now().plusYears(1))
                .authorities(List.of(
                        Authority.builder()
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
    public String verifyMember() {
        final Optional<Member> byAccessToken = memberRepository.findByAccessTokenAndMemberId(
                JwtUtil.getAccessToken(), JwtUtil.getMemberIdFromHeader()
        );

        if(byAccessToken.isEmpty()) {
            return null;
        }
        else
        {
            Member member = byAccessToken.get();

            return member.getRole();
        }
    }

    @Override
    @Transactional
    public MemberAdminResponseDto getMemberInfo(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new MemberEmailNotFoundException(email)
        );

        MemberAdminResponseDto map = modelMapper.map(member, MemberAdminResponseDto.class);

        map.setCreatedAt(member.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh-mm-ss")));

        return map;
    }

    @Override
    @Transactional
    public List<MemberAdminResponseDto> getMembers() {
        List<Member> members = memberRepository.findMemberBy(Sort.by(Sort.Direction.ASC, "createdAt"));
        List<MemberAdminResponseDto> memberAdminResponseDtos = new ArrayList<>();

        for(Member member: members) {
            MemberAdminResponseDto map = modelMapper.map(member, MemberAdminResponseDto.class);

            map.setCreatedAt(member.getCreatedAt().format(DateTimeFormatter.ofPattern("yyy-MM-dd hh-mm-ss")));

            memberAdminResponseDtos.add(map);
        }

        return memberAdminResponseDtos;
    }

    @Override
    public MemberResponseDto getInfo() {
        Member member = memberRepository.findById(JwtUtil.getMemberId()).orElseThrow(
                () -> new MemberIdNotFoundException(JwtUtil.getMemberId())
        );

        return modelMapper.map(member, MemberResponseDto.class);
    }

    @Override
    @Transactional
    public MemberInfoResponseDto editInfo(final MemberEditRequestDto memberEditRequestDto, final String upload) {
        Member member = memberRepository.findById(JwtUtil.getMemberId()).orElseThrow(
                () -> new MemberIdNotFoundException(JwtUtil.getMemberId())
        );

        if(!upload.equals(""))
            member.setImg(upload);

        if(!member.getNickname().equals(memberEditRequestDto.getNickname()) && !memberEditRequestDto.getNickname().trim().isEmpty())
            member.setNickname(memberEditRequestDto.getNickname());
        else
            log.warn("member id: {} input white space name", memberEditRequestDto.getNickname());

        MemberInfoResponseDto map = modelMapper.map(member, MemberInfoResponseDto.class);

        map.setLoginType(getLoginType(member));

        return map;
    }

    @Override
    @Transactional
    public void signOut() {
        Member member = memberRepository.findById(JwtUtil.getMemberId()).orElseThrow(
                () -> new MemberIdNotFoundException(JwtUtil.getMemberId())
        );

        memberRepository.delete(member);

        final DeletedMember map = modelMapper.map(member, DeletedMember.class);

        deletedMemberRepository.save(map);
    }

    @Override
    @Transactional
    public TokenResponseDto reissueAccessToken() {
        String accessToken = JwtUtil.getAccessToken();
        String refreshToken = JwtUtil.getRefreshToken();
        Long id = JwtUtil.getMemberId();

        Member member = memberRepository.findByMemberIdAndAccessTokenAndRefreshToken(id, accessToken,refreshToken)
                .orElseThrow(() -> new MemberIdNotFoundException(id));

        member.setAccessToken(JwtUtil.createJwt(id));
        member.setAccessTokenExpiredAt(LocalDate.now().plusDays(JwtUtil.ACCESS_TOKEN_EXPIRE_TIME));
        memberRepository.save(member);

        return modelMapper.map(member, TokenResponseDto.class);
    }

    @Override
    @Transactional
    public List<Authority> getAuthorities() {
        return memberRepository.findById(JwtUtil.getMemberId()).orElseThrow(
                () -> new MemberIdNotFoundException(JwtUtil.getMemberId())
        ).getAuthorities();
    }

    private String getLoginType(Member member) {
        if(member.getLoginType() == 0L) {
            return "Google";
        } else if(member.getLoginType() == 1L) {
            return "Kakao";
        } else if(member.getLoginType() == 3L) {
            return "apple";
        }
        else
        {
            throw new MemberLoginTypeNotExistException(member.getLoginType());
        }
    }
}
