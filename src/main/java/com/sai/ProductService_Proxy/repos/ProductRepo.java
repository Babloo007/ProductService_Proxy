package com.sai.ProductService_Proxy.repos;

import com.sai.ProductService_Proxy.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    public Product save(Product product);

    Product findProductById(Long id);

    Product findByPriceBetween(double start, double end);

//    Product findByProductTitle(String productTitle);

    String findTitleById(Long id);

//    List<Product> findByIdIsNotNullOrderByPrice(Long id);

//    List<Product> findAllByIsPublicFalse();
}