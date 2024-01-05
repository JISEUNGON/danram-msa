package com.danram.alarm.vo;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageVo {
    private String type;
    private String email;
    private String action;
}
