package com.ecom.mypersonalproject.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductResponseDtos {
    private long productId;
    private String productName;
    private double productPrice;
    private String status;
    private LocalDateTime createdAt;
}
