package com.sameer.demo.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import com.sameer.demo.document.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
    @Override
    Product findOne(String prodName);
    
    Product findByProdName(String prodName);

    @Override
    void delete(Product deleted);
}