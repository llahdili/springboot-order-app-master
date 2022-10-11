package com.springboot.springorderapp.repositories;

import com.springboot.springorderapp.model.PurchaseOrder;
import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<PurchaseOrder, Long> { }

