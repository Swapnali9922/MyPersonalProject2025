package com.ecom.mypersonalproject.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductRequestDtos {
    @NotBlank(message="Product name is required")
    private String productName;

    @Positive(message="price should be greater than 0")
    private double price;

}
