package com.example.burgerqueen_proj.order.entity;


import com.example.burgerqueen_proj.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
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


    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.ORDER_REQUEST;


    // cart ->
    public enum OrderStatus {
        ORDER_REQUEST(1, "주문 요청"),
        ORDER_CONFIRM(2, "주문 확정"),
        ORDER_COMPLETE(3, "주문 처리 완료"),
        ORDER_CANCEL(4, "주문 취소");

        @Getter
        private int stepNumber;

        @Getter
        private String stepDescription;

        OrderStatus(int stepNumber, String stepDescription) {
            this.stepNumber = stepNumber;
            this.stepDescription = stepDescription;
        }
    }


    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderDetails> orderDetails = new ArrayList<>();



    private int totalCount;

    private int totalDiscountPrice;
    private int totalPrice;

    private int stampCount;
    private LocalDateTime createAt;







}
