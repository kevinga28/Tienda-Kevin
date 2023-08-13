package com.tienda.db;

import com.tienda.entities.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;



public interface IProductRepository extends JpaRepository<Product, Integer> {
    List<Product>findAllByPrecioBetween(int lowerPrice, int higherPrice);
    List<Product>findAllByPrecioGreaterThanEqual(int lowerPrice);
     List<Product>findAllByPrecioIsLessThanEqual(int higherPrice);
}
