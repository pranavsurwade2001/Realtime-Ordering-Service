package com.example.cqrs.december.mapper;

import com.example.cqrs.december.dto.OrderItemResponseDTO;
import com.example.cqrs.december.dto.OrderResponseDTO;
import com.example.cqrs.december.entity.Order;
import com.example.cqrs.december.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "items", target = "items")
    OrderResponseDTO toOrderResponseDTO(Order order, List<OrderItem> items);

    OrderItemResponseDTO toOrderItemResponseDTO(OrderItem item);

    List<OrderItemResponseDTO> toOrderItemResponseDTOList(List<OrderItem> items);
}

