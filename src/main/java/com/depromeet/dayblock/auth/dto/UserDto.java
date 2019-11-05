package com.depromeet.dayblock.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserDto {
    protected UserDto(){}
    private String email;
    private String password;
    private String name;
}

