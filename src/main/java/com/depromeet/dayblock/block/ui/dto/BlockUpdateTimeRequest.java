package com.depromeet.dayblock.block.ui.dto;

import lombok.Getter;

@Getter
public class BlockUpdateTimeRequest {

    private Long id;

    private String startTime;

    private String endTime;
}
