package com.danram.comment.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CommentEditResponseDto {
    private Long commentId;
    private String content;
}
