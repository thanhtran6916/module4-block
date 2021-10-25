package com.block.service;

import com.block.model.Block;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IGeneralService<T> {
    Page<T> getAll(Pageable pageable);

    T save(T t);

    void delete(Long id);

    Optional<T> getById(Long id);

}
