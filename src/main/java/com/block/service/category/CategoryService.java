package com.block.service.category;

import com.block.model.Block;
import com.block.model.Category;
import com.block.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Optional<Category> getById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }
}
