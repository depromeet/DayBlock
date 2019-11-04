package com.depromeet.dayblock.block.domain.repository;

import com.depromeet.dayblock.block.domain.Block;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface BlockRepository {

    Long save(Block block);

    List<Block> findByDate(String category, LocalDate date);

    void update(Long id, Block block);

    void updateOrder(Long id, int order);

    void updateTime(Long id, LocalTime startTime, LocalTime endTime);

    void remove(Long id);
}

