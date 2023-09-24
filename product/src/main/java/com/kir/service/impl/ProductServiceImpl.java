package com.kir.service.impl;

import com.kir.entity.Product;
import com.kir.mapper.ProductMapper;
import com.kir.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Override
    public int insertProduct(Product product) {
        return productMapper.insert(product);
    }

    @Override
    public int deleteProduct(int id) {
        return productMapper.deleteById(id);
    }

    @Override
    public List<Product> searchProduct() {
        return productMapper.selectList(null);
    }

    @Override
    public int updateProduct(Product product) {
        return productMapper.updateById(product);
    }
}
