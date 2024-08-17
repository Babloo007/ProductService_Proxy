package com.sai.ProductService_Proxy.repos;

import com.sai.ProductService_Proxy.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    public Product save(Product product);
}