package com.sdata.ecommerce.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author nedli
 */
@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String getHomePage() {
        return "/pages/home";
    }
}
