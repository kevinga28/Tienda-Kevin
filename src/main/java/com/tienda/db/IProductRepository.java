package com.tienda.db;

import com.tienda.entities.Product;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface IProductRepository extends CrudRepository<Product, Integer> {
    List<Product>findAllByPrecioBetween(int lowerPrice, int higherPrice);
    List<Product>findAllByPrecioGreaterThanEqual(int lowerPrice);
     List<Product>findAllByPrecioIsLessThanEqual(int higherPrice);
}
