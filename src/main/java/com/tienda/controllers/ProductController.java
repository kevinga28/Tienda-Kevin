package com.tienda.controllers;

import com.tienda.entities.Category;
import com.tienda.entities.Product;
import com.tienda.service.ICategoryService;
import com.tienda.service.IProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ProductController {
    private final IProductService productService;
    private final ICategoryService categoryService;

    public ProductController(IProductService productService, ICategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/product")
    public String index(Model model) {

        var baseProduct = new Product();
        baseProduct.setCategory(new Category());
        model.addAttribute("productDefault", baseProduct);
        model.addAttribute("products", this.productService.getAll());
        model.addAttribute("categories", this.categoryService.getAll());
        return "product";
    }

}