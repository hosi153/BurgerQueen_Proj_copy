package com.example.burgerqueen_proj.cart.entity;


import com.example.burgerqueen_proj.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
//@EntityListeners(AuditingEntityListener.class)
public class Cart {//extends BasicEntity {
    // 최호균
    // 231005

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long cartId;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;


    public void setMember(Member member){
        this.member = member;
    }



    @OneToMany(mappedBy = "cart", cascade = CascadeType.PERSIST)
    private List <CartProduct> cartProducts = new ArrayList<>();

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
