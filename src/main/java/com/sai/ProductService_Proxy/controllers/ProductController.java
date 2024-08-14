package com.sai.ProductService_Proxy.controllers;

import com.sai.ProductService_Proxy.dtos.ProductDto;
import com.sai.ProductService_Proxy.models.Product;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping("")
    public String getAllProducts(){
        return "Getting all products....";
    }

    @GetMapping("/{id}")
    public String getSingleProduct(@PathVariable("id") Long prodId){
        return "Getting product with id ...."+prodId;
    }

    @PostMapping()
    public String addNewProduct(@RequestBody ProductDto productDto){
        return "Added new product : "+productDto;
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable("id") Long prodId){
        return "Updating the  product : "+prodId;
    }

    @PatchMapping("/{id}")
    public String patchProduct(@PathVariable("id") Long prodId){
        return "Patching the  product : "+prodId;
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long prodId){
        return "Deleting the  product : "+prodId;
    }
}