package com.depromeet.dayblock.block.ui.dto;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Getter
public class BlockUpdateTimeRequest {

    private Long id;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime endTime;
}
