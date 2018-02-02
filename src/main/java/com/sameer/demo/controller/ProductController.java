package com.sameer.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sameer.demo.document.Product;
import com.sameer.demo.repository.ProductRepository;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @RequestMapping(method=RequestMethod.GET, value="/products")
    public Iterable<Product> product() {
        return productRepository.findAll();
    }

    @RequestMapping(method=RequestMethod.POST, value="/products")
    public String save(@RequestBody Product product) {
        productRepository.save(product);

        return product.getId();
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/products/{prodName}")
    @Cacheable(value = "product", key = "#prodName", unless = "#result == null")
    public Product show(@PathVariable String prodName) {
    	System.out.println("Fetching data from database");
        return productRepository.findByProdName(prodName);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/products/{prodName}")
    @CachePut(value = "product", key = "#prodName")
    public Product update(@PathVariable String prodName, @RequestBody Product product) {
        Product prod = productRepository.findByProdName(prodName);
        if(product.getProdName() != null)
            prod.setProdName(product.getProdName());
        if(product.getProdDesc() != null)
            prod.setProdDesc(product.getProdDesc());
        if(product.getProdPrice() != null)
            prod.setProdPrice(product.getProdPrice());
        if(product.getProdImage() != null)
            prod.setProdImage(product.getProdImage());
        productRepository.save(prod);
        return prod;
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/products/{prodName}")
    @CacheEvict(value = "product", key = "#prodName")
    public String delete(@PathVariable String prodName) {
        Product product = productRepository.findByProdName(prodName);
        productRepository.delete(product);

        return "product deleted";
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/products/deleteCache")
    @CacheEvict(value = "product", allEntries = true)
    public void deleteCache() {
        System.out.println("Deleting all cache...");
    }
}