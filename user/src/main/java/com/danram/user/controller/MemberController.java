package com.danram.user.controller;

import com.danram.user.domain.Member;
import com.danram.user.dto.request.login.OauthLoginRequestDto;
import com.danram.user.dto.response.login.LoginResponseDto;
import com.danram.user.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/sign-up")
    public ResponseEntity<LoginResponseDto> signUp(@RequestBody OauthLoginRequestDto dto) {
        Optional<Member> result = memberService.checkDuplicatedEmail(dto.getEmail());

        if(result.isEmpty()) {
            return ResponseEntity.ok(memberService.signUp(dto));
        }
        else
        {
            return ResponseEntity.ok(memberService.signIn(result.get()));
        }
    }

    @GetMapping ("/verify")
    public ResponseEntity<String> tokenExist() {
        return ResponseEntity.ok(memberService.verifyMember());
    }
}
