package com.sdata.ecommerce.controller;

import com.sdata.ecommerce.api.category.SearchCategoryRequest;
import com.sdata.ecommerce.domain.Product;
import com.sdata.ecommerce.service.CategoryService;
import com.sdata.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author nedli
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/add")
    public String addPage(ModelMap model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.search(new SearchCategoryRequest()).getData());
        return "/pages/catalog/product-edit";
    }

    @GetMapping("/image/add")
    public String imagePage(ModelMap model) {
        return "/pages/catalog/product-image-edit";
    }

    @PostMapping("/image/save")
    public String saveImage(@RequestParam(value = "image") MultipartFile image) {
        productService.uploadImage(image);
        return "/pages/catalog/product-image-edit";
    }
}
