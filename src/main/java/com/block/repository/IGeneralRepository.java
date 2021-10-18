package com.block.repository;

import com.block.model.Block;
import java.util.List;


public interface IGeneralRepository<T> {
    List<Block> getAll();

    void save(T t);

    void delete(Long id);

    T getById(Long id);

}
