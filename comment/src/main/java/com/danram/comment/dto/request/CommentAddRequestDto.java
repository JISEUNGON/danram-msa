package com.danram.comment.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CommentAddRequestDto {
    private Long feedId;
    private Long parentId;
    private String content;
}
