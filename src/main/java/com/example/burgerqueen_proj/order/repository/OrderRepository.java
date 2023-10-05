package com.example.burgerqueen_proj.order.repository;

import com.example.burgerqueen_proj.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<Order, Long> {
}
