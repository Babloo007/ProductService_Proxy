package com.sai.ProductService_Proxy.services;

import com.sai.ProductService_Proxy.dtos.ProductDto;
import com.sai.ProductService_Proxy.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();

    Product getSingleProduct(Long productId);

    Product addNewProduct(ProductDto productDto);

    Product updateProduct(Long productId);

    String deleteProduct(Long productId);
}
