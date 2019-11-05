package com.depromeet.dayblock.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TokenDto {
    protected TokenDto(){}
    private String accessToken;
    private String refreshToken;
}
