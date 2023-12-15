package com.danram.user.config;

import com.amazonaws.services.s3.AmazonS3;
import com.danram.user.repository.DeletedMemberRepository;
import com.danram.user.repository.MemberRepository;
import com.danram.user.service.member.MemberService;
import com.danram.user.service.member.MemberServiceImpl;
import com.danram.user.service.s3.S3UploadService;
import com.danram.user.service.s3.S3UploadServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SpringConfig {
    @Bean
    public MemberService memberService(MemberRepository memberRepository, DeletedMemberRepository deletedMemberRepository) {
        return new MemberServiceImpl(memberRepository, deletedMemberRepository);
    }

    @Bean
    public S3UploadService s3UploadService(AmazonS3 amazonS3) {
        return new S3UploadServiceImpl(amazonS3);
    }
}
