package com.example.burgerqueen_proj.order.entity;

import com.example.burgerqueen_proj.product.entity.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderDetailsId;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    public void addOrder(Order order) {
        this.order = order;
        if (!this.order.getOrders().contains(this)) {
            this.order.getOrders().add(this);
        }
    }

//    @ManyToOne
//    @JoinColumn(name = "PRODUCT_ID")
//    private Product product;

//    public void addProduct(Product product) {
//        this.product=product;
//        if (!this.product.getOrderProducts().contains(this)) {
//            this.product.addOrderProduct(this);
//        }
//    }



}
