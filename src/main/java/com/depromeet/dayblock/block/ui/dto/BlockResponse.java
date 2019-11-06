package com.depromeet.dayblock.block.ui.dto;

import com.depromeet.dayblock.block.domain.Block;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Setter
@Component
public class BlockResponse {

    HashMap<String, List<Block>> blocks;

    public void splitByStatus(String key, List<Block> blocks) {
        this.blocks.put(key, blocks);
    }
}
