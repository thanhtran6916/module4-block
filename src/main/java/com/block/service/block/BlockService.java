package com.block.service.block;

import com.block.model.Block;
import com.block.repository.block.IBlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockService implements IBlockService{

    @Autowired
    private IBlockRepository blockRepository;

    @Override
    public List<Block> getAll() {
        return blockRepository.getAll();
    }

    @Override
    public void save(Block block) {
        blockRepository.save(block);
    }

    @Override
    public void delete(Long id) {
        blockRepository.delete(id);
    }

    @Override
    public Block getById(Long id) {
        return blockRepository.getById(id);
    }
}
