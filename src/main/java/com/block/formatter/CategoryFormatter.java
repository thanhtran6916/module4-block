package com.block.formatter;

import com.block.model.Category;
import com.block.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class CategoryFormatter implements Formatter<Category> {

    private ICategoryService categoryService;

    @Autowired
    public CategoryFormatter(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Category parse(String text, Locale locale) throws ParseException {
        Long id = Long.parseLong(text);
        Optional<Category> categoryOptional = categoryService.getById(id);
        if (categoryOptional.isPresent()) {
            return categoryOptional.get();
        }
        return null;
    }

    @Override
    public String print(Category object, Locale locale) {
        return null;
    }
}
