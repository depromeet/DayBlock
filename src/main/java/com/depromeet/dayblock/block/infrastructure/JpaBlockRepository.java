package com.depromeet.dayblock.block.infrastructure;

import com.depromeet.dayblock.block.domain.Block;
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
    public void update(Long id, Block block) {
        em.getTransaction().begin();
        em.merge(block);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void updateLocation(Long id, int location) {
        em.getTransaction().begin();
        Block block = em.find(Block.class, id);
        block.setLocation(location);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void updateTime(Long id, LocalTime startTime, LocalTime endTime) {
        em.getTransaction().begin();
        Block block = em.find(Block.class, id);
        block.setStartTime(startTime);
        block.setEndTime(endTime);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void remove(Long id) {
        Block block = em.find(Block.class, "id");
        em.remove(block);
    }
}
