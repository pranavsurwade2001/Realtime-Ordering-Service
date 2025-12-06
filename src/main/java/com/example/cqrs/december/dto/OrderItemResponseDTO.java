package com.example.cqrs.december.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemResponseDTO {
    private Integer itemId;
    private Integer orderId;
    private String name;
    private Integer quantity;
    private Double price;
}

