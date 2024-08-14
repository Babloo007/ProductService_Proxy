package com.sai.ProductService_Proxy.controllers;

import com.sai.ProductService_Proxy.dtos.ProductDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products/categories")
public class CategoryController {

    @GetMapping("")
    public String getAllCategories(){
        return "Getting all categories....";
    }

    @GetMapping("/{id}")
    public String getProductsInCategory(@PathVariable("id") Long categoryId){
        return "Getting products under category id ...."+categoryId;
    }
}