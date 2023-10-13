package com.example.burgerqueen_proj.order.entity;

import com.example.burgerqueen_proj.cart.entity.Cart;
import com.example.burgerqueen_proj.entity.BasicEntity;
import com.example.burgerqueen_proj.product.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class OrderProduct extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderProductId;

    @Column(nullable = false)
    private int quantity;



    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;


    public void addProduct(Product product) {

        this.product=product;
        if (!this.product.getOrderProducts().contains(this)) {
            this.product.addOrderProduct(this);
        }
    }


    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;


    public void addOrder(Order order) {
        this.order = order;
        if (!this.order.getOrderProducts().contains(this)) {
            this.order.getOrderProducts().add(this);
        }
    }

    @Builder
    public OrderProduct(long orderProductId, int quantity, Product product, Order order) {
        this.orderProductId = orderProductId;
        this.quantity = quantity;
        this.product = product;
        this.order = order;
    }


    //    @ManyToOne
//    @JoinColumn(name = "PRODUCT_ID")
//    private Product product;
//
//    public void addProduct(Product product) {
//        this.product=product;
//        if (!this.product.getOrderProducts().contains(this)) {
//            this.product.addOrderProduct(this);
//        }
//    }



}
