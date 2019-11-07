package com.depromeet.dayblock.block.domain.repository;

import com.depromeet.dayblock.block.domain.Block;
import com.depromeet.dayblock.block.domain.BlockStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface BlockRepository {

    Long save(Block block);

    List<Block> findByDate(String category, LocalDate date);

    void update(Long id, String title, String note, String memo, String link, String category);

    void updateLocation(Long id, int location);

    void updateTime(Long id, LocalTime startTime, LocalTime endTime);

    void updateBlockStatus(Long id, BlockStatus blockStatus);

    void remove(Long id);
}

