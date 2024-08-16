package com.sai.ProductService_Proxy.services;

import com.sai.ProductService_Proxy.clients.fakeStore.client.FakeStoreClient;
import com.sai.ProductService_Proxy.clients.fakeStore.dto.FakeStoreProductDto;
import com.sai.ProductService_Proxy.clients.fakeStore.dto.IClientProductDto;
import com.sai.ProductService_Proxy.dtos.ProductDto;
import com.sai.ProductService_Proxy.models.Categories;
import com.sai.ProductService_Proxy.models.Product;
import jakarta.annotation.Nullable;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements IProductService {

    private final FakeStoreClient fakeStoreClient;

    public FakeStoreProductService(FakeStoreClient fakeStoreClient){
        this.fakeStoreClient = fakeStoreClient;
    }

    @Override
    public List<Product> getAllProducts(){

        List<FakeStoreProductDto> fakeStoreProductDtos= fakeStoreClient.getAllProducts();
        List<Product> products = new ArrayList<>();

        for(FakeStoreProductDto productDto : fakeStoreProductDtos){
            Product product = new Product();
            product.setId(productDto.getId());
            product.setTitle(productDto.getTitle());
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());
            Categories category = new Categories();
            category.setName(String.valueOf(product.getCategory()));
            product.setCategory(category);
            product.setImageUrl(productDto.getImageUrl());

            products.add(product);
        }
        return products;
    }

    @Override
    public Product getSingleProduct(Long productId){

        return getProduct(fakeStoreClient.getSingleProduct(productId));
    }

    @Override
    public Product addNewProduct(Product product){

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        fakeStoreProductDto.setImageUrl(product.getImageUrl());

        return getProduct(fakeStoreClient.addNewProduct(fakeStoreProductDto));
    }

    @Override
    public Product updateProduct(Long productId, Product product){

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        fakeStoreProductDto.setImageUrl(product.getImageUrl());

        return getProduct(fakeStoreClient.updateProduct(productId, fakeStoreProductDto));
    }

    @Override
    public String deleteProduct(Long productId){

        return fakeStoreClient.deleteProduct(productId);
    }

    private Product getProduct(FakeStoreProductDto productDto){
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