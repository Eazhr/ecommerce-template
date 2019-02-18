package com.sdata.ecommerce.service;

import com.sdata.ecommerce.domain.Product;

import java.util.List;

/**
 * @author nedli
 */
public interface ProductService {
    List<Product> queryAll();
}
