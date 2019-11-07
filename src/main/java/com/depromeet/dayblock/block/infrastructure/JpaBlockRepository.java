package com.depromeet.dayblock.block.infrastructure;

import com.depromeet.dayblock.block.domain.Block;
import com.depromeet.dayblock.block.domain.BlockStatus;
import com.depromeet.dayblock.block.domain.repository.BlockRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public class JpaBlockRepository implements BlockRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public Long save(Block block) {
        em.persist(block);
        return block.getId();
    }

    @Override
    public List<Block> findByDate(String category, LocalDate date) {
        String qlString = "select b from Block b where b.scheduledDate = :date and b.category = :category";
        return em.createQuery(qlString, Block.class)
                .setParameter("date", date)
                .setParameter("category", category)
                .getResultList();
    }

    @Override
    public void update(Long id, String title, String note, String memo, String link, String category) {
        Block block = em.find(Block.class, id);
        block.setTitle(title);
        block.setNote(note);
        block.setMemo(memo);
        block.setLink(link);
        block.setCategory(category);
        em.persist(block);
    }

    @Override
    public void updateLocation(Long id, int location) {
        Block block = em.find(Block.class, id);
        block.setLocation(location);
        em.persist(block);
    }

    @Override
    public void updateTime(Long id, LocalTime startTime, LocalTime endTime) {
        Block block = em.find(Block.class, id);
        block.setStartTime(startTime);
        block.setEndTime(endTime);
        em.persist(block);
    }

    @Override
    public void updateBlockStatus(Long id, BlockStatus blockStatus) {
        Block block = em.find(Block.class, id);
        block.setBlockStatus(blockStatus);
        em.persist(block);
    }

    @Override
    public void remove(Long id) {
        Block block = em.find(Block.class, id);
        em.remove(block);
    }
}
