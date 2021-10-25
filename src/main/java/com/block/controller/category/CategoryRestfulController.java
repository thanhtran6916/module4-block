package com.block.controller.category;

import com.block.model.Category;
import com.block.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryRestfulController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<Iterable<Category>> getAll() {
        Iterable<Category> categories = categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Optional<Category> category = categoryService.getById(id);
        if (category.isPresent()) {
            return new ResponseEntity<>(category.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Category> save(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Category> edit(@PathVariable Long id, @RequestBody Category category) {
        Optional<Category> optionalCategory = categoryService.getById(id);
        if (optionalCategory.isPresent()) {
            category.setId(id);
            return new ResponseEntity<>(categoryService.save(category), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Category> delete(@PathVariable Long id) {
        Optional<Category> category = categoryService.getById(id);
        if (category.isPresent()) {
            categoryService.delete(id);
            return new ResponseEntity<>(category.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
