package com.sdata.ecommerce.service;

import com.sdata.ecommerce.domain.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author nedli
 */
public interface ProductService {
    List<Product> queryAll();

    void uploadImage(MultipartFile file);

    void deleteImage(String id);
}
