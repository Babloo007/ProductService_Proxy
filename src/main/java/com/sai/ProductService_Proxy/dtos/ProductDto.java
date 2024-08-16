package com.sai.ProductService_Proxy.dtos;

import com.sai.ProductService_Proxy.models.Categories;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto {

    private Long id;
    private String title;
    private double price;
    private String Description;
    private String category;
    private String imageUrl;
    private RatingDto rating;
}