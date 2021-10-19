package com.block.service.category;

import com.block.model.Category;
import com.block.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService implements ICategoryService{
    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public Page<Category> getAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public void save(Category category) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<Category> getById(Long id) {
        return categoryRepository.findById(id);
    }
}
