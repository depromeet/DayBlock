package com.depromeet.dayblock.block.ui.dto;

import com.depromeet.dayblock.block.domain.BlockPriority;
import com.depromeet.dayblock.block.domain.BlockStatus;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class BlockCreateRequest {

    private String title;

    private String memo;

    private String link;

    private int location;

    private String email;

    private BlockPriority priority;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate scheduledDate;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime endTime;

    private BlockStatus blockStatus;

    private String category;
}
