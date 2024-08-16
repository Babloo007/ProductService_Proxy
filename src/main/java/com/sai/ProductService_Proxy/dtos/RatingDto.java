package com.sai.ProductService_Proxy.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class RatingDto {

    private double rate;
    private double count;
}