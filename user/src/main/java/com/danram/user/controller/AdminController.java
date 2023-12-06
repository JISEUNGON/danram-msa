package com.danram.user.controller;

import com.danram.user.dto.response.member.MemberAdminResponseDto;
import com.danram.user.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {
    private final MemberService memberService;

    @GetMapping("/member")
    public ResponseEntity<MemberAdminResponseDto> getMemberIfo(@RequestParam String email) {
        return ResponseEntity.ok(memberService.getMemberInfo(email));
    }
}
