package com.example.burgerqueen_proj.cart.entity;


import com.example.burgerqueen_proj.product.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter@Setter
@NoArgsConstructor
public class CartProduct {// extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartProductId;


    @Column(nullable = false)
    private int quantity;



    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;


    public void addProduct(Product product) {

        this.product=product;
        if (!this.product.getCartProducts().contains(this)) {
            this.product.addCartProduct(this);
        }
    }


    @ManyToOne
    @JoinColumn(name = "CART_ID")
    private Cart cart;


    public void addCart(Cart cart) {
        this.cart = cart;
        if (!this.cart.getCartProducts().contains(this)) {
            this.cart.getCartProducts().add(this);
        }
    }


    @Builder
    public CartProduct(int quantity, Product product, Cart cart) {
        this.quantity = quantity;
        this.product = product;
        this.cart = cart;
    }
}
