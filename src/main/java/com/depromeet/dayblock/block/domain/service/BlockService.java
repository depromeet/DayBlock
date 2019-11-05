package com.depromeet.dayblock.block.domain.service;

import com.depromeet.dayblock.block.ui.dto.BlockCreateRequest;
import com.depromeet.dayblock.block.ui.dto.BlockResponse;
import com.depromeet.dayblock.block.ui.dto.BlockUpdateRequest;
import com.depromeet.dayblock.block.ui.dto.BlockUpdateTimeRequest;
import com.depromeet.dayblock.block.ui.dto.BlockUpdateLocationRequest;

public interface BlockService {

    Long createBlock(BlockCreateRequest blockRequest);

    BlockResponse getBlocksByDate(String category, String scheduledDate);

    void updateBlock(BlockUpdateRequest blockUpdateRequest);

    void updateBlockLocation(BlockUpdateLocationRequest blockUpdateLocationRequest);

    void updateBlockTime(BlockUpdateTimeRequest blockUpdateTimeRequest);

    void removeBlock(Long id);
}
