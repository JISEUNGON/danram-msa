package com.danram.feed.controller;

import com.danram.feed.dto.request.feed.FeedAddRequestDto;
import com.danram.feed.dto.response.feed.FeedAddResponseDto;
import com.danram.feed.service.feed.FeedService;
import com.danram.feed.service.s3.S3UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
@Slf4j
public class FeedController {
    /**
     * TODO
     * 피드 수정
     * 피드 삭제
     * 피드 추가
     * 피드 신고
     * 알람 보기
     * 알람 추가
     * 알람 삭제
     * 알람 수정
     * 좋아요
     * */
    private final FeedService feedService;
    private final S3UploadService s3UploadService;

    @PostMapping(value = "/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<FeedAddResponseDto> addFeed(@ModelAttribute FeedAddRequestDto dto) throws IOException {
        List<String> files = new ArrayList<>();

        if(dto.getImages().size() > 0) {
            for (MultipartFile file : dto.getImages()) {
                files.add(s3UploadService.upload(file, "/danram/feed", false));
            }
        }

        return ResponseEntity.ok(feedService.addFeed(dto, files));
    }
}
