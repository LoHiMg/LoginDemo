package com.kir.service;

import com.kir.entity.Product;

import java.util.List;

public interface ProductService {

    int insertProduct(Product product);

    int deleteProduct(int id);

    List<Product> searchProduct();

    int updateProduct(Product product);
}
