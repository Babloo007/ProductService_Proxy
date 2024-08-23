package com.sai.ProductService_Proxy.services;

import com.sai.ProductService_Proxy.models.Product;
import com.sai.ProductService_Proxy.repos.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelfProductService implements IProductService{

    private ProductRepo productRepo;

    public SelfProductService(ProductRepo productRepo){
        this.productRepo = productRepo;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product getSingleProduct(Long productId) {
        return productRepo.findProductById(productId);
    }

    @Override
    public Product addNewProduct(Product product) {
        this.productRepo.save(product);
        return product;
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        Product productToUpdate = productRepo.findProductById(productId);
        productToUpdate.setTitle(product.getTitle());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setCategory(product.getCategory());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setImageUrl(product.getImageUrl());
        productRepo.save(productToUpdate);

        return productToUpdate;
    }

    @Override
    public String deleteProduct(Long productId) {

        productRepo.deleteById(productId);
        return "Product with id : "+productId+" deleted successfully";
    }
}
