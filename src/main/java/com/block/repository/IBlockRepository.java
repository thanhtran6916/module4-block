package com.block.repository;

import com.block.model.Block;
import com.block.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IBlockRepository extends PagingAndSortingRepository<Block, Long> {

    Page<Block> findBlockByTitleContaining(String title, Pageable pageable);

    Iterable<Block> findBlockByCategory(Category category);
}
