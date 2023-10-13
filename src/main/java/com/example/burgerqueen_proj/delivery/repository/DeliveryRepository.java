package com.example.burgerqueen_proj.delivery.repository;

import com.example.burgerqueen_proj.delivery.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
