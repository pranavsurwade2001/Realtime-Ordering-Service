package com.example.cqrs.december.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderRequestDTO {

    @NotNull
    private Integer orderId;

    @Positive
    @Nullable
    private Double amount;

    @Nullable
    private String status;

    @Nullable
    private String address;

    @NotBlank
    private String updatedBy;

}
