package com.sdata.ecommerce.controller;

import com.sdata.ecommerce.domain.Product;
import com.sdata.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author nedli
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/add")
    public String addPage(ModelMap model) {
        model.addAttribute("product", new Product());
        return "/pages/catalog/product-edit";
    }
}
