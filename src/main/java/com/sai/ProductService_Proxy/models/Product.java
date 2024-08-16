package com.sai.ProductService_Proxy.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    private Long id;
    private String title;
    private double price;
    private String Description;
    private Categories category;
    private String imageUrl;
}