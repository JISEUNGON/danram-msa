package com.danram.feed.service.feed;

import com.danram.feed.domain.Feed;
import com.danram.feed.domain.Image;
import com.danram.feed.dto.request.feed.FeedAddRequestDto;
import com.danram.feed.dto.response.feed.FeedAddResponseDto;
import com.danram.feed.exception.feed.FeedMakeException;
import com.danram.feed.repository.FeedRepository;
import com.danram.feed.repository.ImageRepository;
import com.danram.feed.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.danram.feed.config.MapperConfig.modelMapper;

@RequiredArgsConstructor
@Slf4j
public class FeedServiceImpl implements FeedService {
    private final FeedRepository feedRepository;
    private final ImageRepository imageRepository;

    @Override
    @Transactional
    public FeedAddResponseDto addFeed(final FeedAddRequestDto dto, List<String> files) {
        FeedAddResponseDto map;

        //이미지 리스트 생성
        List<Image> images = new ArrayList<>();

        if(files.isEmpty()) {
            if(dto.getContent().isBlank()) {// 이미지 X, 글 X
                throw new FeedMakeException("no image and no content");
            }
            else
            { //이미지 없고 글 있는거
                for(String file: files)
                    images.add(imageRepository.save(
                            Image.builder()
                                    .memberId(JwtUtil.getMemberId())
                                    .description(dto.getMemberEmail() + "의 피드  사진")
                                    .memberEmail(dto.getMemberEmail())
                                    .imageUrl(file)
                                    .build()
                    ));

                Feed feed = feedRepository.save(
                        Feed.builder()
                                .memberId(JwtUtil.getMemberId())
                                .memberEmail(dto.getMemberEmail())
                                .content(dto.getContent())
                                .partyId(dto.getPartyId())
                                .images(images)
                                .build()
                );

                map = modelMapper.map(feed, FeedAddResponseDto.class);

                return map;
            }
        }
        else
        { //이미지 있고 컨텐트는 노상관
            for(String image: files) { //이미지 저장 =>  객체로 만들기
                images.add(
                        imageRepository.save(
                                Image.builder()
                                        .imageUrl(image)
                                        .memberEmail(dto.getMemberEmail())
                                        .memberId(JwtUtil.getMemberId())
                                        .description(dto.getMemberEmail() + "의 피드 이미지")
                                        .build()
                        )
                );
            }

            Feed feed = feedRepository.save(
                    Feed.builder()
                            .memberId(JwtUtil.getMemberId())
                            .memberEmail(dto.getMemberEmail())
                            .deletedAt(null)
                            .content(dto.getContent().isBlank() ? null : dto.getContent())
                            .partyId(dto.getPartyId())
                            .images(images)
                            .build()
            );

            map = modelMapper.map(feed, FeedAddResponseDto.class);

            return map;
        }
    }
}
