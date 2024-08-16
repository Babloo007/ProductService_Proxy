package com.sai.ProductService_Proxy.services;

import com.sai.ProductService_Proxy.dtos.ProductDto;
import com.sai.ProductService_Proxy.models.Categories;
import com.sai.ProductService_Proxy.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {

    private RestTemplateBuilder restTemplateBuilder;

    public ProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public List<Product> getAllProducts(){

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto[]> productDtos = restTemplate.getForEntity("https://fakestoreapi.com/products", ProductDto[].class);

        List<Product> products= new ArrayList<>();

        for(ProductDto productDto : productDtos.getBody()){
            products.add(getProduct(productDto));
        }
        return products;
    }

    @Override
    public Product getSingleProduct(Long productId){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto> productDto = restTemplate.getForEntity("https://fakestoreapi.com/products/{productId}", ProductDto.class, productId);

        Product product = getProduct(productDto.getBody());
        return product;
    }

    @Override
    public Product addNewProduct(ProductDto productDto){
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.postForEntity("https://fakestoreapi.com/products", productDto, ProductDto.class);

        Product product = getProduct(productDto);
        return product;
    }

    @Override
    public String updateProduct(Long productId){
        return null;
    }

    @Override
    public String deleteProduct(Long productId){
        return null;
    }

    private Product getProduct(ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        Categories category = new Categories();
        category.setName(String.valueOf(product.getCategory()));
        product.setCategory(category);
        product.setImageUrl(productDto.getImageUrl());

        return product;
    }
}