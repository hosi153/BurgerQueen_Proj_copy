package com.example.burgerqueen_proj.cart.repository;

import com.example.burgerqueen_proj.cart.entity.Cart;
import com.example.burgerqueen_proj.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository <Cart, Long> {
    Cart findByMember(Member member);
}
