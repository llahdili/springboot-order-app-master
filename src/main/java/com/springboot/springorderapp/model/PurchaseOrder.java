package com.springboot.springorderapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "purchase_order")
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate createdDate;
    private BigDecimal totalAmount;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @ManyToOne
    @JsonIgnore
    private Customer customer;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "lineItems_id", referencedColumnName = "id")
    private Set<OrderLineItem> lineItems = new HashSet<>();

}
