package com.example.cqrs.december.service.core;

import com.example.cqrs.december.dto.CreateOrderRequest;
import com.example.cqrs.december.dto.OrderResponseDTO;
import com.example.cqrs.december.dto.UpdateOrderRequestDTO;

import java.util.List;

public interface OrderService {

    OrderResponseDTO createOrder(CreateOrderRequest req);

    OrderResponseDTO getOrder(Integer orderId);

    List<OrderResponseDTO> getAllOrders();

    OrderResponseDTO updateOrder(UpdateOrderRequestDTO updateOrderRequestDTO);

    void deleteOrder(Integer orderId);
}
