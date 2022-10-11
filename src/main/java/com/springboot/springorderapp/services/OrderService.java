package com.springboot.springorderapp.services;

import com.springboot.springorderapp.model.PurchaseOrder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface OrderService {
    PurchaseOrder saveOrder(PurchaseOrder purchaseOrder) throws Exception;
    List<PurchaseOrder> getAllOrders();
    PurchaseOrder getOrderById(long id);
    PurchaseOrder updateOrder(PurchaseOrder purchaseOrder, long id) throws Exception;
    void deleteOrder(long id);

    PurchaseOrder cancelOrder(long id);
}
