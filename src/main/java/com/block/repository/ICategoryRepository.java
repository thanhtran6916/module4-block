package com.block.repository;

import com.block.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICategoryRepository extends PagingAndSortingRepository<Category, Long> {

}
