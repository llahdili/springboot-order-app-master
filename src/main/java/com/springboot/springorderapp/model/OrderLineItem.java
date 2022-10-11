package com.springboot.springorderapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "order_line_item")
public class OrderLineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @JsonIgnore
    @ManyToOne
    private PurchaseOrder purchaseOrder;
    @JsonIgnore
    @ManyToOne
    private Product product;
}
