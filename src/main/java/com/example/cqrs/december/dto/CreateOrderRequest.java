package com.example.cqrs.december.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {

    @NotNull
    private String userId;

    @NotNull
    @Positive
    private Double amount;

    private String address;

    @NotBlank(message = "createdBy is required")
    private String createdBy;

    @NotEmpty
    @Valid
    private List<OrderItemDTO> items;
}

