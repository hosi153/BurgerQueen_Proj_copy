package com.example.burgerqueen_proj.delivery.repository;

import com.example.burgerqueen_proj.cart.entity.Cart;
import com.example.burgerqueen_proj.delivery.entity.Delivery;
import com.example.burgerqueen_proj.member.entity.Member;
import com.example.burgerqueen_proj.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {


    Delivery findByOrder(Order order);

    Delivery findAllByMember(Member member);

    Delivery findByMember(Member member);


}
