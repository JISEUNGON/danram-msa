package com.danram.feed.dto.response;

import com.danram.feed.domain.Image;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class FeedEditResponseDto {
    private Long feedId;
    private Long memberId;
    private String memberName;
    private Long likeNum;
    private LocalDateTime updatedAt;
    private List<Image> images;
    private String content;
}
