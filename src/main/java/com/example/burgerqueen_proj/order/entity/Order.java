package com.example.burgerqueen_proj.order.entity;


import com.example.burgerqueen_proj.entity.BasicEntity;
import com.example.burgerqueen_proj.member.entity.Member;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Order extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;


    private int totalCount;
    private int totalDiscountPrice;
    private int totalPrice;







    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    public void setMember(Member member) {
        this.member = member;
    }
    public void addOrderProduct(OrderProduct orderProduct) {
        this.orderProducts.add(orderProduct);
        if (orderProduct.getOrder() != this) {
            orderProduct.addOrder(this);
        }
    }








    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.ORDER_REQUEST;








//    private int stampCount;


    // cart ->
    public enum OrderStatus {
        ORDER_REQUEST(1, "주문 요청"),
        //        ORDER_CONFIRM(2, "주문 확정"),
//        ORDER_COMPLETE(3, "주문 처리 완료"),
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







}
