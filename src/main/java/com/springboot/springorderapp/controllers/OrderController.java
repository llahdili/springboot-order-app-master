package com.springboot.springorderapp.controllers;

import com.springboot.springorderapp.model.PurchaseOrder;
import com.springboot.springorderapp.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("/add")
    public ResponseEntity<PurchaseOrder> createOrder(@RequestBody PurchaseOrder purchaseOrder) throws Exception {
        purchaseOrder.setCreatedDate(LocalDate.now());
        return new ResponseEntity<>(orderService.saveOrder(purchaseOrder), HttpStatus.CREATED);
    }

   @GetMapping("/all")
    public ResponseEntity<List<PurchaseOrder>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<PurchaseOrder> getOrderById(@PathVariable("id") long id) {
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<PurchaseOrder> updateOrder(@RequestBody PurchaseOrder purchaseOrder, @PathVariable("id") long id) throws Exception {
        return new ResponseEntity<>(orderService.updateOrder(purchaseOrder, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") long id) {
        // delete employee from DB
        orderService.deleteOrder(id);
        return new ResponseEntity<>("Order deleted successfully!", HttpStatus.OK);
    }

   @PutMapping("/cancel/{id}")
    public ResponseEntity<PurchaseOrder> cancelOrder(@PathVariable("id") long id) {
        return new ResponseEntity<>(orderService.cancelOrder(id), HttpStatus.OK);
    }

}
