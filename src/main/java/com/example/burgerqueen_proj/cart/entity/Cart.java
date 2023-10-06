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
    private List <CartDetail> cartDetails = new ArrayList<>();

    public void addCartProduct(CartDetail cartDetail){
        this.cartDetails.add(cartDetail);
        if (cartDetail.getCart() != this){
            cartDetail.addCart(this);
        }
    }
//
//
//    private int totalCount;
//
//    private int totalDiscountPrice;
//    private int totalPrice;
//
//    private int stampCount;

}
