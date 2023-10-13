package com.example.burgerqueen_proj.order.repository;

import com.example.burgerqueen_proj.cart.entity.Cart;
import com.example.burgerqueen_proj.member.entity.Member;
import com.example.burgerqueen_proj.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<Order, Long> {
    Order findByMember(Member member);

}
