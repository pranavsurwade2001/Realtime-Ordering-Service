package com.example.cqrs.december.service.impl;

import com.example.cqrs.december.dto.CreateOrderRequest;
import com.example.cqrs.december.dto.OrderItemDTO;
import com.example.cqrs.december.dto.OrderResponseDTO;
import com.example.cqrs.december.dto.UpdateOrderRequestDTO;
import com.example.cqrs.december.entity.Order;
import com.example.cqrs.december.entity.OrderItem;
import com.example.cqrs.december.exception.NotFoundException;
import com.example.cqrs.december.mapper.OrderMapper;
import com.example.cqrs.december.repository.OrderItemRepository;
import com.example.cqrs.december.repository.OrderRepository;
import com.example.cqrs.december.service.core.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.cqrs.december.enums.OrderStatus.CREATED;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepo;
    private final OrderItemRepository itemRepo;
    private final OrderMapper mapper;

    @Override
    @Transactional
    public OrderResponseDTO createOrder(CreateOrderRequest req) {

        Order order = new Order();
        order.setUserId(req.getUserId());
        order.setAmount(req.getAmount());
        order.setStatus(CREATED.name());
        order.setAddress(req.getAddress());
        order.setCreatedBy(req.getCreatedBy());
        order.setUpdatedBy(req.getCreatedBy());

        order = orderRepo.save(order);

        // Save items
        for (OrderItemDTO dto : req.getItems()) {
            OrderItem item = new OrderItem();
            item.setOrderId(order.getOrderId());
            item.setName(dto.getName());
            item.setQuantity(dto.getQuantity());
            item.setPrice(dto.getPrice());
            item.setCreatedBy(dto.getCreatedBy());
            item.setUpdatedBy(dto.getCreatedBy());
            itemRepo.save(item);
        }

        return getOrder(order.getOrderId());
    }

    @Override
    public OrderResponseDTO getOrder(Integer orderId) {

        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found with id: " + orderId));

        List<OrderItem> items = itemRepo.findByOrderId(orderId);

        return mapper.toOrderResponseDTO(order, items);
    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {

        List<Order> orders = orderRepo.findAll();

        return orders.stream()
                .map(order -> {
                    List<OrderItem> items = itemRepo.findByOrderId(order.getOrderId());
                    return mapper.toOrderResponseDTO(order, items);
                })
                .toList();
    }

    @Override
    @Transactional
    public OrderResponseDTO updateOrder(UpdateOrderRequestDTO req) {

        if (req.getOrderId() == null) {
            throw new IllegalArgumentException("orderId is required");
        }

        Order order = orderRepo.findById(req.getOrderId())
                .orElseThrow(() -> new NotFoundException("Order not found with id: " + req.getOrderId()));


        if (req.getAmount() != null)
            order.setAmount(req.getAmount());

        if (req.getStatus() != null)
            order.setStatus(req.getStatus());

        if (req.getAddress() != null)
            order.setAddress(req.getAddress());

        if (req.getUpdatedBy() != null)
            order.setUpdatedBy(req.getUpdatedBy());

        orderRepo.save(order);
        return getOrder(req.getOrderId());
    }

    @Override
    @Transactional
    public void deleteOrder(Integer orderId) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found with id: " + orderId));
        itemRepo.deleteAll(itemRepo.findByOrderId(orderId));
        orderRepo.delete(order);
    }
}

