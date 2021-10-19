package com.block.service.block;

import com.block.model.Block;
import com.block.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBlockService extends IGeneralService<Block> {
    Page<Block> findBlockByTitleContaining(String title, Pageable pageable);
}
