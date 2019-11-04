package com.depromeet.dayblock.block.domain.service;

import com.depromeet.dayblock.block.domain.Block;
import com.depromeet.dayblock.block.domain.BlockStatus;
import com.depromeet.dayblock.block.domain.repository.BlockRepository;
import com.depromeet.dayblock.block.ui.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BlockServiceImpl implements BlockService{

    private final BlockResponse blockResponse;
    private final BlockRepository blockRepository;

    @Override
    public Long createBlock(BlockCreateRequest blockRequest) {
        return blockRepository.save(blockRequest.getBlock());
    }

    @Override
    public BlockResponse getBlocksByDate(String category, String scheduledDate) {
        List<Block> blocks = blockRepository.findByDate(category, LocalDate.parse(scheduledDate));
        blockResponse
                .splitByStatus("todo", blocks.stream().filter(b->b.getBlockStatus() == BlockStatus.TODO)
                        .collect(Collectors.toList()));
        blockResponse
                .splitByStatus("urgency", blocks.stream().filter(b->b.getBlockStatus() == BlockStatus.URGENCY)
                        .collect(Collectors.toList()));
        blockResponse
                .splitByStatus("complete", blocks.stream().filter(b->b.getBlockStatus() == BlockStatus.COMPLETE)
                        .collect(Collectors.toList()));
        return blockResponse;
    }

    @Override
    public void updateBlock(BlockUpdateRequest blockUpdateRequest) {
        blockRepository.update(blockUpdateRequest.getId(), blockUpdateRequest.getBlock());
    }

    @Override
    public void updateBlockOrder(BlockUpdateOrderRequest blockUpdateOrderRequest) {
        blockRepository.updateOrder(blockUpdateOrderRequest.getId(), blockUpdateOrderRequest.getOrder());
    }

    @Override
    public void updateBlockTime(BlockUpdateTimeRequest blockUpdateTimeRequest) {
        blockRepository.updateTime(
                blockUpdateTimeRequest.getId(),
                LocalTime.parse(blockUpdateTimeRequest.getStartTime()),
                LocalTime.parse(blockUpdateTimeRequest.getEndTime())
        );
    }

    @Override
    public void removeBlock(Long id) {
        blockRepository.remove(id);
    }
}
