package com.sai.ProductService_Proxy.controllers;

import com.sai.ProductService_Proxy.dtos.ProductDto;
import com.sai.ProductService_Proxy.models.Product;
import com.sai.ProductService_Proxy.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;

    ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts(){
        try {
            return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long prodId){
        try {
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("Accept", "application/json");
            headers.add("Content-Type", "application/json");
            headers.add("Auth-Token", "HeyAccess");
            if(prodId < 1) throw new IllegalArgumentException("Illegal product ID");
            Product product = productService.getSingleProduct(prodId);

            ResponseEntity<Product> responseEntity = new ResponseEntity<>(product, headers, HttpStatus.OK);
            return responseEntity;
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody ProductDto productDto){
        Product product = productService.addNewProduct(productDto);
        ResponseEntity<Product> responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        return responseEntity;
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