package com.example.burgerqueen_proj.cart.entity;


import com.example.burgerqueen_proj.entity.BasicEntity;
import com.example.burgerqueen_proj.order.entity.Order;
import com.example.burgerqueen_proj.product.entity.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter@Setter
public class CartDetail{// extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartDetailId;


    @Column(nullable = false)
    private int quantity;



    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;


    public void addProduct(Product product) {
        this.product=product;
        if (!this.product.getCartDetails().contains(this)) {
            this.product.addCartDetail(this);
        }
    }


    @ManyToOne
    @JoinColumn(name = "CART_ID")
    private Cart cart;


    public void addCart(Cart cart) {
        this.cart = cart;
        if (!this.cart.getCartDetails().contains(this)) {
            this.cart.getCartDetails().add(this);
        }
    }





}
