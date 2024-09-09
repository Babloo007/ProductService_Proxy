package com.sai.ProductService_Proxy.controllers;

import com.sai.ProductService_Proxy.models.Product;
import com.sai.ProductService_Proxy.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductControllerTest {

    @MockBean
    private IProductService productService;

    @Autowired
    private ProductController productController;

    @Test
    void test_whenGetAllProductsIsCalled_returnListOfProducts() {

        List<Product> products = new ArrayList<>();

        Product prod1 = new Product();
        prod1.setTitle("test1");
        prod1.setDescription("test1");

        Product prod2 = new Product();
        prod2.setTitle("test2");
        prod2.setDescription("test2");

        products.add(prod1);
        products.add(prod2);

        when(productService.getAllProducts()).thenReturn(products);

        ResponseEntity<List<Product>> responseEntity = productController.getAllProducts();

        assertNotNull(responseEntity);
        assertEquals(responseEntity.getBody().size(), 2);
    }

    @Test
    void test_whenGetAllProductsIsCalled_ReturnException() {
        // RULE
        when(productService.getAllProducts())
                .thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class,
                () -> productController.getAllProducts());
    }
}