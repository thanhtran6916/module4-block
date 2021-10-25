package com.block.controller.block;

import com.block.model.Block;
import com.block.service.block.IBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/blocks")
public class BlockRestfulController {

    @Autowired
    private IBlockService blockService;

    @GetMapping
    public ResponseEntity<Page<Block>> findAll(@PageableDefault(size = 3)Pageable pageable) {
        Page<Block> blocks = blockService.findAll(pageable);
        return new ResponseEntity<>(blocks, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Block> findById(@PathVariable Long id) {
        Optional<Block> block = blockService.getById(id);
        if (block.isPresent()) {
            return new ResponseEntity<>(block.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Block> save(@RequestBody Block block) {
        return new ResponseEntity<>(blockService.save(block), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Block> edit(@PathVariable Long id, @RequestBody Block block) {
        Optional<Block> optionalBlock = blockService.getById(id);
        if (optionalBlock.isPresent()) {
            block.setId(id);
            blockService.save(block);
            return new ResponseEntity<>(block, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Block> delete(@PathVariable Long id) {
        Optional<Block> block = blockService.getById(id);
        if (block.isPresent()) {
            blockService.delete(id);
            return new ResponseEntity<>(block.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
