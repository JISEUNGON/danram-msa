package com.danram.user.dto.request.login;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OauthLoginRequestDto {
    private String nickname;
    private String email;
    private String profileImg;
    private Long loginType;
}
