package com.sai.ProductService_Proxy.clients.fakeStore.dto;

import com.sai.ProductService_Proxy.dtos.RatingDto;
import com.sai.ProductService_Proxy.clients.fakeStore.dto.IClientProductDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FakeStoreProductDto implements IClientProductDto {

    private Long id;
    private String title;
    private double price;
    private String Description;
    private String category;
    private String imageUrl;
    private RatingDto rating;
}