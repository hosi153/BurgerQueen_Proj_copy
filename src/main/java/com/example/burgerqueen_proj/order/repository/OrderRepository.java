package com.example.burgerqueen_proj.order.repository;

import com.example.burgerqueen_proj.cart.entity.Cart;
import com.example.burgerqueen_proj.member.entity.Member;
import com.example.burgerqueen_proj.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository  extends JpaRepository<Order, Long> {
    List<Order> findByMember(Member member);

}
