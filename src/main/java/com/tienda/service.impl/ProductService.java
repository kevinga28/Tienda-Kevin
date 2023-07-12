package com.tienda.service.impl;

import com.tienda.db.IProductRepository;
import com.tienda.entities.Product;
import com.tienda.service.IProductService;
import org.springframework.stereotype.Service;


@Service
public class ProductService extends BaseService<Product, Integer> implements IProductService {
    public ProductService(IProductRepository productRepository) {
        super(productRepository);
    }

}
