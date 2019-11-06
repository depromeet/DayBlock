package com.depromeet.dayblock.block.domain.service;

import com.depromeet.dayblock.block.domain.BlockStatus;
import com.depromeet.dayblock.block.ui.dto.BlockCreateRequest;
import com.depromeet.dayblock.block.ui.dto.BlockResponse;
import com.depromeet.dayblock.block.ui.dto.BlockUpdateRequest;
import com.depromeet.dayblock.block.ui.dto.BlockUpdateTimeRequest;

import java.time.LocalDate;

public interface BlockService {

    Long createBlock(BlockCreateRequest blockRequest);

    BlockResponse getBlocksByDate(String category, LocalDate scheduledDate);

    void updateBlock(BlockUpdateRequest blockUpdateRequest);

    void updateBlockLocation(Long id, int location);

    void updateBlockTime(BlockUpdateTimeRequest blockUpdateTimeRequest);

    void updateBlockStatus(Long id, BlockStatus blockStatus);

    void removeBlock(Long id);
}
