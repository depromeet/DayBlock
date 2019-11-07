package com.depromeet.dayblock.block.ui.dto;

import lombok.Getter;


@Getter
public class BlockUpdateRequest {

    private Long id;

    private String title;

    private String note;

    private String memo;

    private String link;

    private String category;
}
