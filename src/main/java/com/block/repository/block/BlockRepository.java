package com.block.repository.block;

import com.block.model.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Repository
@Transactional
public class BlockRepository implements IBlockRepository{
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Block> getAll() {
        TypedQuery<Block> query = entityManager.createQuery("select b from Block as b", Block.class);
        return query.getResultList();
    }

    @Override
    public void save(Block block) {
        Boolean isSave = false;
        if (block.getId() == null) {
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("insert_block");
            query.registerStoredProcedureParameter("titleInput", String.class, ParameterMode.IN);
            query.setParameter("titleInput", block.getTitle());
            query.registerStoredProcedureParameter("contentInput", String.class, ParameterMode.IN);
            query.setParameter("contentInput", block.getContent());
            query.registerStoredProcedureParameter("imageInput", String.class, ParameterMode.IN);
            query.setParameter("imageInput", block.getImage());
            isSave = query.execute();
        } else {
            entityManager.merge(block);
        }
    }

    @Override
    public void delete(Long id) {
        Block block = entityManager.find(Block.class, id);
        entityManager.remove(block);
    }

    @Override
    public Block getById(Long id) {
        return entityManager.find(Block.class, id);
    }
}
