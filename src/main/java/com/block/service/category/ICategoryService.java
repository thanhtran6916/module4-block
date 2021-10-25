package com.block.service.category;

import com.block.model.Block;
import com.block.model.Category;
import com.block.service.IGeneralService;

public interface ICategoryService extends IGeneralService<Category> {

    Iterable<Category> findAll();

}
