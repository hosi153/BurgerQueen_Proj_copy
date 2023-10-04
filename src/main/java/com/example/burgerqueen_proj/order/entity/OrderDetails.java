package com.example.burgerqueen_proj.order.entity;

import com.example.burgerqueen_proj.order.entity.Order;

import javax.persistence.*;

@Entity
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderDetailsId;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;


    private long productId;

    private int count;

    private int price;

    private int discountPrice;



}
