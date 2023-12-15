package com.danram.user.controller;

import com.danram.user.dto.request.token.TokenReissueResponseDto;
import com.danram.user.dto.response.token.TokenResponseDto;
import com.danram.user.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
@Slf4j
public class TokenController {
    private final MemberService memberService;

    @PostMapping("/reissue")
    public ResponseEntity<TokenResponseDto> reissueAccessToken() {
        return ResponseEntity.ok(memberService.reissueAccessToken());
    }
}
