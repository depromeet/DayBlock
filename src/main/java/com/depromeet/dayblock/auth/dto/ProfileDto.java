package com.depromeet.dayblock.auth.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProfileDto {
    protected ProfileDto(){}
    private String email;
    private String name;
    private String thumbImageUrl ;
    private String imageUrl ;
}

