package com.block.controller.block;

import com.block.model.Block;
import com.block.model.BlockForm;
import com.block.model.Category;
import com.block.service.block.IBlockService;
import com.block.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/block")
public class BlockController {
    @Autowired
    private IBlockService blockService;

    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute(name = "categories")
    public Page<Category> categories(Pageable pageable) {
        return categoryService.getAll(pageable);
    }


    @Value("${upload-file}")
    private String uploadFile;

    @GetMapping
    public ModelAndView showAll(@RequestParam("q") Optional<String> q, @PageableDefault(size = 3) Pageable pageable) {
        Page<Block> blocks = null;
        if (q.isPresent()) {
            String title = q.get();
            blocks = blockService.findBlockByTitleContaining(title, pageable);
        } else {
            blocks = blockService.getAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/block/list");
        modelAndView.addObject("blocks", blocks);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreate() {
        BlockForm blockForm = new BlockForm();
        ModelAndView modelAndView = new ModelAndView("/block/create");
        modelAndView.addObject("blockForm", blockForm);
        return modelAndView;
    }

    @PostMapping("/create")
    public String createBlock(@ModelAttribute("blockForm") BlockForm blockForm) {
        MultipartFile file = blockForm.getImage();
        Block block = new Block();
        String fileName = file.getOriginalFilename();
        try {
            byte[] bytes = file.getBytes();
            File file1 = new File(uploadFile + fileName);
            FileCopyUtils.copy(bytes, file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        block.setImage(fileName);
        block.setTitle(blockForm.getTitle());
        block.setContent(blockForm.getContent());
        block.setCategory(blockForm.getCategory());
        blockService.save(block);
        return "redirect:/block";
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable("id") Long id, Model model) {
        Block block = null;
        Optional<Block> block1 = blockService.getById(id);
        if (block1.isPresent()) {
            block = block1.get();
            model.addAttribute("block", block);
            return "/block/edit";
        }
        return "404";
    }

    @PostMapping("/edit/{id}")
    public String editBlock(@ModelAttribute("block") Block block) {
        blockService.save(block);
        return "redirect:/block";
    }

    @GetMapping("/info/{id}")
    public String showInfo(@PathVariable("id") Long id, Model model) {
        Block block = null;
        Optional<Block> block1 = blockService.getById(id);
        if (block1.isPresent()) {
            block = block1.get();
            model.addAttribute("block", block);
            return "/block/info";
        }
        return "404";
    }

    @GetMapping("/delete/{id}")
    public String showDelete(@PathVariable("id") Long id, Model model) {
        Block block = null;
        Optional<Block> block1 = blockService.getById(id);
        if (block1.isPresent()) {
            block = block1.get();
            model.addAttribute("block", block);
            return "/block/delete";
        }
        return "404";
    }

    @PostMapping("/delete/{id}")
    public String deleteBlock(@PathVariable("id") Long id) {
        blockService.delete(id);
        return "redirect:/block";
    }

}
