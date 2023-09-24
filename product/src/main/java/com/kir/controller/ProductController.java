package com.kir.controller;

import com.kir.entity.Product;
import com.kir.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/product/all")
    public List<Product> searchProduct(){
        return productService.searchProduct();
    }

    @PostMapping("/product")
    public int insertProduct(@RequestBody Product product){
            return productService.insertProduct(product);
    }

    @DeleteMapping("/product/{id}")
    public int deleteProduct(@PathVariable int id){
        return productService.deleteProduct(id);
    }

    @PutMapping("/product")
    public int updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }
}
