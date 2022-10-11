package com.springboot.springorderapp.services.impl;

import com.springboot.springorderapp.exception.MethodNotAllowedException;
import com.springboot.springorderapp.exception.ResourceNotFoundException;
import com.springboot.springorderapp.model.OrderLineItem;
import com.springboot.springorderapp.model.PurchaseOrder;
import com.springboot.springorderapp.model.Status;
import com.springboot.springorderapp.repositories.CustomerRepository;
import com.springboot.springorderapp.repositories.OrderRepository;
import com.springboot.springorderapp.services.OrderService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public PurchaseOrder saveOrder(PurchaseOrder purchaseOrder) throws Exception {
        if (purchaseOrder.getCustomer() == null || purchaseOrder.getCustomer().getId() <= 0) {
            throw new Exception("The order should be associated with a customer id");
        }
        customerRepository.findById(purchaseOrder.getCustomer().getId())
                .orElseThrow(() -> new Exception("The associated customer does not exist"));
        if (purchaseOrder.getLineItems() == null || purchaseOrder.getLineItems().size() == 0)   {
            throw new Exception("The order should have at least one product item");
        }
        BigDecimal totalAmount = purchaseOrder.getLineItems().stream()
                .map(orderLineItem -> orderLineItem.getProduct().getPrice()
                        .multiply(BigDecimal.valueOf(orderLineItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        purchaseOrder.setTotalAmount(totalAmount);

        return orderRepository.save(purchaseOrder);
    }

    @Override
    public List<PurchaseOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public PurchaseOrder getOrderById(long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PurchaseOrder.class, "id", String.valueOf(id)));
    }

    @Override
    public PurchaseOrder updateOrder(PurchaseOrder purchaseOrder, long id) throws Exception {
        PurchaseOrder existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PurchaseOrder.class, "id", String.valueOf(id)));
        if (purchaseOrder.getCustomer() != null && !Objects.equals(existingOrder.getCustomer().getId(), purchaseOrder.getCustomer().getId())) {
            throw new Exception("The customer of the order cannot be changed");
        }
        customerRepository.findById(purchaseOrder.getCustomer().getId())
                .orElseThrow(() -> new Exception("The associated customer does not exist"));
        if (purchaseOrder.getLineItems() == null || purchaseOrder.getLineItems().size() == 0) {
            throw new Exception("The order should have at least one product item");
        }
        return orderRepository.findById(id)
                .map(or -> {
                    or.setCreatedDate(purchaseOrder.getCreatedDate());
                    or.setLineItems(purchaseOrder.getLineItems());
                    or.setStatus(purchaseOrder.getStatus());
                    return orderRepository.save(or);
                })
                .orElseThrow(() -> new ResourceNotFoundException(PurchaseOrder.class, "id", String.valueOf(id)));
    }
    @Override
    public void deleteOrder(long id) {
        orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PurchaseOrder.class, "id", String.valueOf(id)));
        orderRepository.deleteById(id);
    }

    @Override
    public PurchaseOrder cancelOrder(long id) {
        PurchaseOrder purchaseOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PurchaseOrder.class, "Id", id));
        if (purchaseOrder.getStatus() == Status.IN_PROGRESS) {
            purchaseOrder.setStatus(Status.CANCELLED);
            orderRepository.save(purchaseOrder);
        } else {
            throw new MethodNotAllowedException("You can't cancel an order that is in the " + purchaseOrder.getStatus() + " status");
        }
        return purchaseOrder;
    }
}
