package com.springboot.springorderapp.controllers;

import com.springboot.springorderapp.model.OrderLineItem;
import com.springboot.springorderapp.services.OrderLineItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderlines")
public class OrderLineController {
    private final OrderLineItemService orderLineService;
    @Autowired
    public OrderLineController(OrderLineItemService orderLineService) {
        super();
        this.orderLineService = orderLineService;
    }

    @PostMapping("/add")
    public ResponseEntity<OrderLineItem> saveOrderLine(@RequestBody OrderLineItem orderLine) {
        return new ResponseEntity<>(orderLineService.saveOrderLineItem(orderLine), HttpStatus.CREATED);
    }

     @GetMapping("/all")
    public ResponseEntity<List<OrderLineItem>> getAllOrderLines() {
        return new ResponseEntity<>(orderLineService.getAllOrderLineItems(), HttpStatus.OK);
    }

     @GetMapping("/orderline/{id}")
    public ResponseEntity<OrderLineItem> getOrderLineById(@PathVariable("id") long id) {
        return new ResponseEntity<>(orderLineService.getOrderLineItemById(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OrderLineItem> updateOrderLine(@RequestBody OrderLineItem orderLine, @PathVariable("id") long id) {
        return new ResponseEntity<>(orderLineService.updateOrderLineItem(orderLine, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrderLine(@PathVariable("id") long id) {
        // delete employee from DB
        orderLineService.deleteOrderLineItem(id);
        return new ResponseEntity<>("OrderLine deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("/order/{orderId}/product/{productId}/show")
    public ResponseEntity<OrderLineItem> showOrderLine(@PathVariable("orderId") long orderId, @PathVariable("productId") long productId) {
        return new ResponseEntity<>(orderLineService.showOrderLineByOrderIdAndProduct(orderId, productId), HttpStatus.OK);
    }
}
