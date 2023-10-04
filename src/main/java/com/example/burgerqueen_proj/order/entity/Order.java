package com.example.burgerqueen_proj.order.entity;


import com.example.burgerqueen_proj.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    @OneToMany(mappedBy = "order")
    private List<OrderDetails> orders = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;


    private int totalCount;

    private int totalDiscountPrice;
    private int totalPrice;

    private int stampCount;
    private LocalDateTime createAt;








}
