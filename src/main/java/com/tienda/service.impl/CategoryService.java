package com.tienda.service.impl;

import com.tienda.db.ICategoryRepository;
import com.tienda.entities.Category;
import com.tienda.service.ICategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends BaseService<Category, Integer> implements ICategoryService {
    public CategoryService(ICategoryRepository categoryRepository) {
        super(categoryRepository);
    }

}
