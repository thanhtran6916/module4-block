package com.block.controller;

import com.block.model.Block;
import com.block.model.BlockForm;
import com.block.service.block.IBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/block")
public class BlockController {
    @Autowired
    private IBlockService blockService;

    @Value("${upload-file}")
    private String uploadFile;

    @GetMapping
    public ModelAndView showAll() {
        List<Block> blocks = blockService.getAll();
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
        blockService.save(block);
        return "redirect:/block";
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable("id") Long id, Model model) {
        Block block = blockService.getById(id);
        model.addAttribute("block", block);
        return "/block/edit";
    }

    @PostMapping("/edit/{id}")
    public String editBlock(@ModelAttribute("block") Block block) {
        blockService.save(block);
        return "redirect:/block";
    }

    @GetMapping("/info/{id}")
    public String showInfo(@PathVariable("id") Long id, Model model) {
        Block block = blockService.getById(id);
        model.addAttribute("block", block);
        return "/block/info";
    }

    @GetMapping("/delete/{id}")
    public String showDelete(@PathVariable("id") Long id, Model model) {
        Block block = blockService.getById(id);
        model.addAttribute("block", block);
        return "/block/delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteBlock(@PathVariable("id") Long id) {
        blockService.delete(id);
        return "redirect:/block";
    }

}
