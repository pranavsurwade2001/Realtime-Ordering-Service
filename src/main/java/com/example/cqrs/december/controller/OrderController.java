package com.example.cqrs.december.controller;

import com.example.cqrs.december.common.ApiResponse;
import com.example.cqrs.december.dto.CreateOrderRequest;
import com.example.cqrs.december.dto.OrderResponseDTO;
import com.example.cqrs.december.dto.UpdateOrderRequestDTO;
import com.example.cqrs.december.service.core.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Log4j2
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<OrderResponseDTO> createOrder(@Valid @RequestBody CreateOrderRequest req) {
        return new ApiResponse<>(Boolean.TRUE, "Order created", orderService.createOrder(req));
    }

    @GetMapping("/{orderId}")
    public ApiResponse<OrderResponseDTO> getOrder(@PathVariable Integer orderId) {
        return new ApiResponse<>(Boolean.TRUE, "Order fetched", orderService.getOrder(orderId));
    }

    @GetMapping
    public ApiResponse<List<OrderResponseDTO>> getAllOrders() {
        return new ApiResponse<>(Boolean.TRUE, "Orders fetched", orderService.getAllOrders());
    }

    @PutMapping
    public ApiResponse<OrderResponseDTO> updateOrder(@Valid @RequestBody UpdateOrderRequestDTO req) {
        return new ApiResponse<>(Boolean.TRUE, "Order updated", orderService.updateOrder(req));
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Integer orderId) {
        orderService.deleteOrder(orderId);
    }

}
