package com.depromeet.dayblock.block.domain.service;

import com.depromeet.dayblock.block.domain.Block;
import com.depromeet.dayblock.block.domain.BlockStatus;
import com.depromeet.dayblock.block.domain.repository.BlockRepository;
import com.depromeet.dayblock.block.ui.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BlockServiceImpl implements BlockService{

    private final BlockResponse blockResponse;
    private final BlockRepository blockRepository;
    // private final userRepository userRepository;

    @Override
    public Long createBlock(BlockCreateRequest blockRequest) {
        Block block = Block.builder()
                .title(blockRequest.getTitle())
                .memo(blockRequest.getMemo())
                .link(blockRequest.getLink())
                .location(blockRequest.getLocation())
                // .owner(userRepository.findByEmail(blockRequest.getEmail()))
                .priority(blockRequest.getPriority())
                .scheduledDate(blockRequest.getScheduledDate())
                .startTime(blockRequest.getStartTime())
                .endTime(blockRequest.getEndTime())
                .blockStatus(blockRequest.getBlockStatus())
                .category(blockRequest.getCategory())
                .build();
        block.validate();
        return blockRepository.save(block);
    }

    @Override
    public BlockResponse getBlocksByDate(String category, LocalDate scheduledDate) {
        blockResponse.setBlocks(new HashMap<>());
        List<Block> blocks = blockRepository.findByDate(category, scheduledDate);
        if (!blocks.isEmpty()) {
            blockResponse
                    .splitByStatus("todo", blocks.stream().filter(b->b.getBlockStatus() == BlockStatus.TODO)
                            .collect(Collectors.toList()));
            blockResponse
                    .splitByStatus("urgency", blocks.stream().filter(b->b.getBlockStatus() == BlockStatus.URGENCY)
                            .collect(Collectors.toList()));
            blockResponse
                    .splitByStatus("complete", blocks.stream().filter(b->b.getBlockStatus() == BlockStatus.COMPLETE)
                            .collect(Collectors.toList()));
        }
        return blockResponse;
    }

    @Override
    public void updateBlock(BlockUpdateRequest blockUpdateRequest) {
        blockRepository.update(
                blockUpdateRequest.getId(),
                blockUpdateRequest.getTitle(),
                blockUpdateRequest.getMemo(),
                blockUpdateRequest.getLink(),
                blockUpdateRequest.getPriority(),
                blockUpdateRequest.getCategory()
        );
    }

    @Override
    public void updateBlockLocation(Long id, int location) {
        blockRepository.updateLocation(id, location);
    }

    @Override
    public void updateBlockTime(BlockUpdateTimeRequest blockUpdateTimeRequest) {
        blockRepository.updateTime(
                blockUpdateTimeRequest.getId(),
                blockUpdateTimeRequest.getStartTime(),
                blockUpdateTimeRequest.getEndTime()
        );
    }

    @Override
    public void updateBlockStatus(Long id, BlockStatus blockStatus) {
        blockRepository.updateBlockStatus(id, blockStatus);
    }

    @Override
    public void removeBlock(Long id) {
        blockRepository.remove(id);
    }
}
