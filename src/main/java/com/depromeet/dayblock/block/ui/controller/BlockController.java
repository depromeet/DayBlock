package com.depromeet.dayblock.block.ui.controller;

import com.depromeet.dayblock.block.domain.Block;
import com.depromeet.dayblock.block.domain.BlockStatus;
import com.depromeet.dayblock.block.domain.service.BlockService;
import com.depromeet.dayblock.block.ui.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Api(value = "Block")
@CrossOrigin(origins = {"*"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/block")
public class BlockController {

    private final BlockService blockService;

    @ApiOperation("새로운 블럭을 생성합니다. 인증이 필요한 요청입니다.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public Long createBlock(@RequestBody BlockCreateRequest blockCreateRequest) {
        return blockService.createBlock(blockCreateRequest);
    }

    @ApiOperation("날짜에 따라 블럭을 조회합니다. 인증이 필요한 요청입니다.")
    @GetMapping("/{category}")
    public HashMap<String, List<Block>> getBlocks(
            @PathVariable String category,
            @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
            @RequestParam LocalDate scheduledDate) {
        return blockService.getBlocksByDate(category, scheduledDate);
    }

    @ApiOperation("기존 블럭을 수정합니다. 인증이 필요한 요청입니다.")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/")
    public void updateBlock(@RequestBody BlockUpdateRequest blockUpdateRequest) {
        blockService.updateBlock(blockUpdateRequest);
    }

    @ApiOperation("기존 블럭의 순서를 갱신합니다. 인증이 필요한 요청입니다.")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update/location/{id}")
    public void updateBlockLocation(@PathVariable Long id, @RequestParam int location) {
        blockService.updateBlockLocation(id, location);
    }

    @ApiOperation("기존 블럭의 수행 시간을 갱신합니다. 인증이 필요한 요청입니다.")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update/time")
    public void updateBlockTime(@RequestBody BlockUpdateTimeRequest blockUpdateTimeRequest) {
        blockService.updateBlockTime(blockUpdateTimeRequest);
    }

    @ApiOperation("기존 블럭의 상태를 수정합니다. 인증이 필요한 요청입니다.")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update/status/{id}")
    public void updateBlockStatus(@PathVariable Long id, @RequestParam BlockStatus blockStatus) {
        blockService.updateBlockStatus(id, blockStatus);
    }

    @ApiOperation("블럭을 삭제합니다. 인증이 필요한 요청입니다.")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteBlock(@PathVariable Long id) {
        blockService.removeBlock(id);
    }
}
