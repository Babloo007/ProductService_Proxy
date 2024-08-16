package com.sai.ProductService_Proxy.controllers;

import com.sai.ProductService_Proxy.clients.fakeStore.dto.FakeStoreProductDto;
import com.sai.ProductService_Proxy.clients.fakeStore.dto.IClientProductDto;
import com.sai.ProductService_Proxy.dtos.ProductDto;
import com.sai.ProductService_Proxy.models.Categories;
import com.sai.ProductService_Proxy.models.Product;
import com.sai.ProductService_Proxy.services.FakeStoreProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    FakeStoreProductService productService;

    ProductController(FakeStoreProductService productService){
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

        Product productIn = new Product();
        productIn.setId(productDto.getId());
        productIn.setImageUrl(productDto.getImageUrl());
        productIn.setDescription(productDto.getDescription());
        productIn.setTitle(productDto.getTitle());
        productIn.setPrice(productDto.getPrice());
        productIn.setCategory(new Categories());
        productIn.getCategory().setName(productDto.getCategory());

        try {
            Product product = productService.addNewProduct(productIn);
            ResponseEntity<Product> responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable("id") Long prodId, @RequestBody ProductDto productDto){
        return "Updating Product...";
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> patchProduct(@PathVariable("id") Long prodId, @RequestBody ProductDto productDto){

        Product productIn = new Product();
        productIn.setId(productDto.getId());
        productIn.setImageUrl(productDto.getImageUrl());
        productIn.setDescription(productDto.getDescription());
        productIn.setTitle(productDto.getTitle());
        productIn.setPrice(productDto.getPrice());
        productIn.setCategory(new Categories());
        productIn.getCategory().setName(productDto.getCategory());

        try {
            Product product = productService.updateProduct(prodId, productIn);
            ResponseEntity<Product> responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
            return responseEntity;
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long prodId){
        try {
            ResponseEntity<String> responseEntity = new ResponseEntity<>(productService.deleteProduct(prodId), HttpStatus.OK);
            return responseEntity;
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}