package com.danram.user.config;

import com.danram.user.repository.MemberRepository;
import com.danram.user.service.member.MemberService;
import com.danram.user.service.member.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SpringConfig {
    @Bean
    public MemberService memberService(MemberRepository memberRepository) {
        return new MemberServiceImpl(memberRepository);
    }
}
