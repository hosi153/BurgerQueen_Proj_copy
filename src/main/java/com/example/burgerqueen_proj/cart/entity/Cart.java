package com.example.burgerqueen_proj.cart.entity;


import com.example.burgerqueen_proj.entity.BasicEntity;
import com.example.burgerqueen_proj.member.entity.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
public class Cart extends BasicEntity {
    // 최호균
    // 231005

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long cartId;

    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;


    public void setMember(Member member){
        this.member = member;
    }



    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartProduct> cartProducts = new ArrayList<>();

    public void updateCartProducts(List<CartProduct> cartProducts) {
        this.cartProducts = new ArrayList<>();
        cartProducts.forEach(this::addCartProduct);
    }


    public void addCartProduct(CartProduct cartProduct){
        this.cartProducts.add(cartProduct);
        if (cartProduct.getCart() != this){
            cartProduct.addCart(this);
        }
    }
//
//
    private int totalCount=0;

    public void sumTotalCount(){
        for (CartProduct cartProduct : cartProducts){
            totalCount+=cartProduct.getQuantity();
        }
    }
//
//    private int totalDiscountPrice;
//    public void sumTotalDiscountPrice(){
//        for (CartProduct cartProduct : cartProducts){
//            totalCount+= (int) cartProduct.getProduct().getDiscountPrice();
//        }
//    }
    private int totalPrice;
    public void sumTotalPrice(){
        for (CartProduct cartProduct : cartProducts){
            totalCount+= (int) cartProduct.getProduct().getProductPrice();
        }
    }
//
//    private int stampCount;

}
