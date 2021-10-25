package com.block.service.block;

import com.block.model.Block;
import com.block.model.Category;
import com.block.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBlockService extends IGeneralService<Block> {
    Page<Block> findBlockByTitleContaining(String title, Pageable pageable);

    Iterable<Block> findAll();

    Iterable<Block> findBlockByCategory(Category category);

    Page<Block> findAll(Pageable pageable);

}
