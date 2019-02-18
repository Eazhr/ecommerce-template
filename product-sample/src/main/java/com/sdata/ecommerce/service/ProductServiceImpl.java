package com.sdata.ecommerce.service;

import com.sdata.ecommerce.domain.Product;
import com.sdata.ecommerce.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author nedli
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> queryAll() {
        return productMapper.queryAll();
    }
}
