package com.sdata.ecommerce.service;

import com.sdata.ecommerce.domain.Product;
import com.sdata.ecommerce.domain.ProductImage;
import com.sdata.ecommerce.mapper.ProductImageMapper;
import com.sdata.ecommerce.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author nedli
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductImageMapper productImageMapper;

    @Autowired
    private FastDFSClientHelper fastDFSClientHelper;

    @Override
    public List<Product> queryAll() {
        return productMapper.queryAll();
    }

    @Override
    public void uploadImage(MultipartFile file) {
        String fullPath = fastDFSClientHelper.uploadFile(file);
        ProductImage image = new ProductImage();
        image.setPath(fullPath);
        image.setTitle(file.getOriginalFilename());
        image.setProductId(null); //TODO: set real product id.
        productImageMapper.insert(image);
    }

    @Override
    public void deleteImage(String id) {
        ProductImage image = productImageMapper.selectByPrimaryKey(id);

        if (image != null) {
            String path = image.getPath();
            fastDFSClientHelper.deleteFile(path);
            productImageMapper.deleteByPrimaryKey(id);
        }
    }
}
