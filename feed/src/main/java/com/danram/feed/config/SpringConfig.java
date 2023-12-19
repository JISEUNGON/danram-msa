package com.danram.feed.config;

import com.amazonaws.services.s3.AmazonS3;
import com.danram.feed.repository.FeedRepository;
import com.danram.feed.repository.ImageRepository;
import com.danram.feed.service.feed.FeedService;
import com.danram.feed.service.feed.FeedServiceImpl;
import com.danram.feed.service.s3.S3UploadService;
import com.danram.feed.service.s3.S3UploadServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    private final FeedRepository feedRepository;
    private final ImageRepository imageRepository;

    public SpringConfig(final FeedRepository feedRepository,
                        final ImageRepository imageRepository) {
        this.feedRepository = feedRepository;
        this.imageRepository = imageRepository;
    }

    @Bean
    public S3UploadService s3UploadService(AmazonS3 amazonS3) {
        return new S3UploadServiceImpl(amazonS3);
    }

    @Bean
    public FeedService feedService() {
        return new FeedServiceImpl(feedRepository, imageRepository);
    }
}
