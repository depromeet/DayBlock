package com.depromeet.dayblock.block.ui.dto;

import com.depromeet.dayblock.block.domain.BlockPriority;

import lombok.Getter;


@Getter
public class BlockUpdateRequest {

    private Long id;

    private String title;

    private String memo;

    private String link;

    private BlockPriority priority;

    private String category;
}
