package com.depromeet.dayblock.block.ui.dto;

import com.depromeet.dayblock.block.domain.Block;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
public class BlockResponse {

    Map<String, List<Block>> blocks;

    public void splitByStatus(String key, List<Block> blocks) {
        this.blocks.put(key, blocks);
    }
}
