package com.tienda.controllers;

import com.tienda.entities.Category;
import com.tienda.service.ICategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {
    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category")
    public String index(Model model) {

        model.addAttribute("categoryDefault", new Category());
        model.addAttribute("categories", this.categoryService.getAll());
        return "category";
    }

}