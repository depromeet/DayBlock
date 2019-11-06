package com.depromeet.dayblock;

import com.depromeet.dayblock.block.domain.Block;
import com.depromeet.dayblock.block.domain.BlockPriority;
import com.depromeet.dayblock.block.domain.BlockStatus;
import com.depromeet.dayblock.block.domain.repository.BlockRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BlockRepositoryTests {

    @Autowired
    BlockRepository blockRepository;

    @Test
    @Transactional
    @Rollback
    public void testSaveBlock() throws Exception {
        //given
        Block block = Block.builder()
                .title("자료 업로드하기")
                .memo("SSL 관련 자료 google drive에 업로드하기")
                .link("https://www.google.com")
                .location(0)
                .priority(BlockPriority.HIGH)
                .scheduledDate(LocalDate.parse("2019-11-10"))
                .startTime(LocalTime.parse("10:15"))
                .endTime(LocalTime.parse("11:00"))
                .blockStatus(BlockStatus.TODO)
                .category("My Schedule")
                .build();

        //when
        Long saveId = blockRepository.save(block);
        List<Block> findBlock = blockRepository.findByDate("My Schedule", LocalDate.parse("2019-11-10"));

        //then
        assertEquals(findBlock.get(0).getId(), saveId);
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateBlock() throws Exception {
        //given
        Block block = Block.builder()
                .title("자료 업로드하기")
                .memo("SSL 관련 자료 google drive에 업로드하기")
                .link("https://www.google.com")
                .location(0)
                .priority(BlockPriority.HIGH)
                .scheduledDate(LocalDate.parse("2019-11-10"))
                .startTime(LocalTime.parse("10:15"))
                .endTime(LocalTime.parse("11:00"))
                .blockStatus(BlockStatus.TODO)
                .category("My Schedule")
                .build();
        Long saveId = blockRepository.save(block);

        //when
        blockRepository.update(
                saveId,
                "새 자료 업로드하기",
                "HTTPS 관련 자료 google drive에 업로드하기",
                "https://www.google.com",
                BlockPriority.MEDIUM,
                "My Daily Schedule"
                );
        List<Block> findBlock = blockRepository.findByDate("My Daily Schedule", LocalDate.parse("2019-11-10"));

        //then
        assertEquals(findBlock.get(0).getTitle(), "새 자료 업로드하기");
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateTimeBlock() throws Exception {
        //given
        Block block = Block.builder()
                .title("자료 업로드하기")
                .memo("SSL 관련 자료 google drive에 업로드하기")
                .link("https://www.google.com")
                .location(0)
                .priority(BlockPriority.HIGH)
                .scheduledDate(LocalDate.parse("2019-11-10"))
                .startTime(LocalTime.parse("10:15"))
                .endTime(LocalTime.parse("11:00"))
                .blockStatus(BlockStatus.TODO)
                .category("My Schedule")
                .build();
        Long saveId = blockRepository.save(block);

        //when
        blockRepository.updateTime(saveId, LocalTime.parse("11:10"), LocalTime.parse("12:00"));
        List<Block> findBlock = blockRepository.findByDate("My Schedule", LocalDate.parse("2019-11-10"));

        //then
        assertEquals(findBlock.get(0).getStartTime(), LocalTime.parse("11:10"));
    }

    @Test
    @Transactional
    @Rollback
    public void testRemoveBlock() throws Exception {
        //given
        Block block = Block.builder()
                .title("자료 업로드하기")
                .memo("SSL 관련 자료 google drive에 업로드하기")
                .link("https://www.google.com")
                .location(0)
                .priority(BlockPriority.HIGH)
                .scheduledDate(LocalDate.parse("2019-11-10"))
                .startTime(LocalTime.parse("10:15"))
                .endTime(LocalTime.parse("11:00"))
                .blockStatus(BlockStatus.TODO)
                .category("My Schedule")
                .build();
        Long saveId = blockRepository.save(block);

        //when
        blockRepository.remove(saveId);
        List<Block> findBlock = blockRepository.findByDate("My Schedule", LocalDate.parse("2019-11-10"));

        //then
        assert(findBlock.isEmpty());
    }
}
