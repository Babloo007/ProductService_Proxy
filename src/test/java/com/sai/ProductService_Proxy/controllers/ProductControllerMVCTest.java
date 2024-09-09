package com.sai.ProductService_Proxy.controllers;

import com.sai.ProductService_Proxy.models.Product;
import com.sai.ProductService_Proxy.services.IProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@WebMvcTest(ProductController.class)
public class ProductControllerMVCTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllProducts() throws Exception {

        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());
        products.add(new Product());

        when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(products)));
    }

    @Test
    void getSingleProduct() throws Exception {

        Product product = new Product();

        when(productService.getSingleProduct(any(Long.class))).thenReturn(product);

        mockMvc.perform(get("/products/2"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(product)));
    }

    @Test
    void addNewProduct() throws Exception {

        Product productToCreate = new Product();
        productToCreate.setTitle("iPhone 15 Pro Max");
        productToCreate.setImageUrl("some image");
        productToCreate.setDescription("Best iPhone Ever");

        Product productToExpect = new Product();
        productToExpect.setId(1L);
        productToExpect.setTitle("iPhone 15 Pro Max");
        productToExpect.setImageUrl("some image");
        productToExpect.setDescription("Best iPhone Ever");

        when(productService.addNewProduct(any(Product.class))).thenReturn(productToExpect);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productToCreate)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productToExpect)))
                .andExpect(jsonPath("$.title", is("iPhone 15 Pro Max")))
                .andExpect(jsonPath("$.length()", is(9)));
    }

    @Test
    void updateProduct() throws Exception {

        Product productToUpdate = new Product();
        productToUpdate.setId(3L);
        productToUpdate.setTitle("testproduct");
        productToUpdate.setDescription("testproduct");

        when(productService.updateProduct(any(Long.class), any(Product.class))).thenReturn(productToUpdate);

        mockMvc.perform(patch("/products/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productToUpdate))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("testproduct")))
                .andExpect(jsonPath("$.description", is("testproduct")));
    }

    @Test
    void deleteProduct() throws Exception {

        when(productService.deleteProduct(any(Long.class))).thenReturn("Product is deleted");

        MvcResult mvcResult = mockMvc.perform(delete("/products/3")
                        .param("id", "1L"))
                .andExpect(status().isOk())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        assertEquals(result, "Product is deleted");
    }
}