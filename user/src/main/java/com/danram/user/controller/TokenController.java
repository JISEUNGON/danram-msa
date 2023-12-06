package com.danram.user.controller;

import com.danram.user.dto.request.token.TokenReissueResponseDto;
import com.danram.user.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
@Slf4j
public class TokenController {
    private final MemberService memberService;

    /**
     * TODO 토큰 만료 시 재발급
     * */

    @GetMapping("/reissue")
    public ResponseEntity<TokenReissueResponseDto> reissueToken() {
        return ResponseEntity.ok(memberService.reissueToken());
    }
}
