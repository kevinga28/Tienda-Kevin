package com.tienda.service.impl;

 

import com.tienda.db.IProductRepository;
import com.tienda.entities.Product;
import com.tienda.service.IProductService;
import org.springframework.stereotype.Service;
//import com.google.cloud.firestore.CollectionReference;
//import com.google.cloud.firestore.Firestore;
 

import java.util.List;
import java.util.Optional;
//import org.springframework.beans.factory.annotation.Autowired;

 

@Service
public class ProductService extends BaseService<Product, Integer> implements IProductService {


    //here we have to create this variable because of syntax we can't see the this.repository as a IproductRepository
    private final IProductRepository productRepository;

 

    public ProductService(IProductRepository productRepository) {
        super(productRepository);
        this.productRepository = productRepository;
    }

 

    public List<Product> getProductsWithFilters(Optional<Integer> lowerPrice, Optional<Integer> higherPrice) {

 

        if (lowerPrice.isPresent() && higherPrice.isPresent()) {
            return this.productRepository.findAllByPrecioBetween(lowerPrice.get(), higherPrice.get());
        }

 

        if (lowerPrice.isPresent()) {
            return this.productRepository.findAllByPrecioGreaterThanEqual(lowerPrice.get());
        }

 

        if (higherPrice.isPresent()) {
            return this.productRepository.findAllByPrecioIsLessThanEqual(higherPrice.get());
        }

 

        return (List<Product>) this.productRepository.findAll();
    }
//     
//@Autowired
//	private Firestore firestore;
//
//
//	public CollectionReference getCollection() {
//		return firestore.collection("products");
//	}
}
