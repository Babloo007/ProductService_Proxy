package com.sai.ProductService_Proxy.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{

    private String title;
    private double price;
    private String Description;
    @ManyToOne(cascade = CascadeType.ALL)
    private Categories category;
    private String imageUrl;
}