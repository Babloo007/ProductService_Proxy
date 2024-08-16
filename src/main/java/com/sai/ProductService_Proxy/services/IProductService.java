package com.sai.ProductService_Proxy.services;

import com.sai.ProductService_Proxy.clients.fakeStore.dto.FakeStoreProductDto;
import com.sai.ProductService_Proxy.clients.fakeStore.dto.IClientProductDto;
import com.sai.ProductService_Proxy.dtos.ProductDto;
import com.sai.ProductService_Proxy.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();

    Product getSingleProduct(Long productId);

    Product addNewProduct(Product product);

    Product updateProduct(Long productId, Product product);

    String deleteProduct(Long productId);
}
