package com.danram.feed.dto.response.feed;

import com.danram.feed.domain.Image;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FeedAllInfoResponseDto {
    private Long feedId;
    private Long memberId;
    private String memberName;
    private LocalDateTime updatedAt;
    private List<Image> images;
    private String content;
    private Long likeCount;
    private Boolean hasNextSlice;
}
