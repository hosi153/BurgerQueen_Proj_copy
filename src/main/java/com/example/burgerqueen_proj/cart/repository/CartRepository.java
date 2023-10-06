package com.example.burgerqueen_proj.cart.repository;

import com.example.burgerqueen_proj.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository <Cart, Long> {
}
