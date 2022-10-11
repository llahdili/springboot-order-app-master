package com.springboot.springorderapp.services;

import com.springboot.springorderapp.model.OrderLineItem;
import com.springboot.springorderapp.model.Product;

import java.util.List;

public interface OrderLineItemService {
    OrderLineItem saveOrderLineItem(OrderLineItem orderLineItem);
    List<OrderLineItem> getAllOrderLineItems();
    OrderLineItem getOrderLineItemById(long id);
    OrderLineItem updateOrderLineItem(OrderLineItem orderLineItem, long id);
    void deleteOrderLineItem(long id);
    OrderLineItem showOrderLineByOrderIdAndProduct(long orderId, long productId);
}
