package com.example.burgerqueen_proj.delivery.entity;


import com.example.burgerqueen_proj.entity.BasicEntity;
import com.example.burgerqueen_proj.order.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Delivery extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long deliveryId;

    @OneToOne
    @JoinColumn(name = "orderId")
    private Order order;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;


    public enum DeliveryStatus{
        DELIVERY_READY(1,"배달 요청"),
        DELIVERY_START(2,"배달 중"),
        DELIVERY_SUCCESS(3, "배달 완료");

            @Getter
        private int statusNumber;

            @Getter
        private String statusDescription;

        DeliveryStatus(int statusNumber, String statusDescription) {
            this.statusNumber = statusNumber;
            this.statusDescription = statusDescription;
        }
    }



}
