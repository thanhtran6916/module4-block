package com.block.service.block;

import com.block.model.Block;
import com.block.repository.IBlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlockService implements IBlockService{

    @Autowired
    private IBlockRepository blockRepository;

    @Override
    public Page<Block> getAll(Pageable pageable) {
        return blockRepository.findAll(pageable);
    }

    @Override
    public void save(Block block) {
        blockRepository.save(block);
    }

    @Override
    public void delete(Long id) {
        blockRepository.deleteById(id);
    }

    @Override
    public Optional<Block> getById(Long id) {
        return blockRepository.findById(id);
    }

    @Override
    public Page<Block> findBlockByTitleContaining(String title, Pageable pageable) {
        Page<Block> blocks = blockRepository.findBlockByTitleContaining(title, pageable);
        return blocks;
    }
}
