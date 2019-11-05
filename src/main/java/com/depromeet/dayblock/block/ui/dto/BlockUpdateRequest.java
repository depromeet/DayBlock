package com.depromeet.dayblock.block.ui.dto;

import com.depromeet.dayblock.block.domain.Block;
import lombok.Getter;

@Getter
public class BlockUpdateRequest {

    private Long id;

    private Block block;
}
