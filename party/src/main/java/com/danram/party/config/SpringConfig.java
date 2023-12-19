package com.danram.party.config;

import com.amazonaws.services.s3.AmazonS3;
import com.danram.party.repository.PartyMemberRepository;
import com.danram.party.repository.PartyRepository;
import com.danram.party.service.party.PartyService;
import com.danram.party.service.party.PartyServiceImpl;
import com.danram.party.service.s3.S3UploadService;
import com.danram.party.service.s3.S3UploadServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SpringConfig {
    private final PartyRepository partyRepository;
    private final PartyMemberRepository partyMemberRepository;

    @Bean
    public PartyService partyService() {
        return new PartyServiceImpl(partyRepository, partyMemberRepository);
    }

    @Bean
    public S3UploadService s3UploadService(AmazonS3 amazonS3) {
        return new S3UploadServiceImpl(amazonS3);
    }
}
