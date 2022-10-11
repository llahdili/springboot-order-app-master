package com.springboot.springorderapp.services.impl;

import com.springboot.springorderapp.exception.ResourceNotFoundException;
import com.springboot.springorderapp.model.OrderLineItem;
import com.springboot.springorderapp.model.Product;
import com.springboot.springorderapp.model.PurchaseOrder;
import com.springboot.springorderapp.repositories.OrderLineItemRepository;
import com.springboot.springorderapp.repositories.OrderRepository;
import com.springboot.springorderapp.services.OrderLineItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderLineItemServiceImpl implements OrderLineItemService {
    private final OrderLineItemRepository orderLineItemRepository;
    private final OrderRepository orderRepository;

    public OrderLineItemServiceImpl(OrderLineItemRepository orderLineItemRepository, OrderRepository orderRepository) {
        this.orderLineItemRepository = orderLineItemRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderLineItem saveOrderLineItem(OrderLineItem orderLineItem) {
        return orderLineItemRepository.save(orderLineItem);
    }

    @Override
    public List<OrderLineItem> getAllOrderLineItems() {
        return orderLineItemRepository.findAll();
    }

    @Override
    public OrderLineItem getOrderLineItemById(long id) {
        return orderLineItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(OrderLineItem.class, "id", String.valueOf(id)));
    }

    @Override
    public OrderLineItem updateOrderLineItem(OrderLineItem orderLineItem, long id) {
        return orderLineItemRepository.findById(id)
                .map(orderLine -> {
                    orderLine.setQuantity(orderLineItem.getQuantity());
                    return orderLineItemRepository.save(orderLine);
                })
                .orElseThrow(() -> new ResourceNotFoundException(OrderLineItem.class, "id", String.valueOf(id)));
    }

    @Override
    public void deleteOrderLineItem(long id) {
        orderLineItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(OrderLineItem.class, "id", String.valueOf(id)));
        orderLineItemRepository.deleteById(id);
    }

    @Override
    public OrderLineItem showOrderLineByOrderIdAndProduct(long orderId, long productId) {
        PurchaseOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException(PurchaseOrder.class, "id", String.valueOf(orderId)));
        Optional<OrderLineItem> orderLineItem = order.getLineItems().stream()
                .filter(lineItem -> lineItem.getProduct().getId().equals(productId)).findFirst();
        // todo return exception or null?
        if (orderLineItem.isEmpty()) {
            return null;
        }
        return orderLineItem.get();
    }
}
