package com.example.burgerqueen_proj.promotion.entity;


import com.example.burgerqueen_proj.product.entity.Product;
import com.example.burgerqueen_proj.member.entity.Role;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
public class PromotionDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long promotionDetailsId;

    @ManyToOne
    @JoinColumn(name = "PROMOTION_ID")
    private Promotion promotion;

//    @ManyToOne
//    @JoinColumn(name = "PROMOTION_TYPE")
//    private Promotion.PromotionType promotionType;

//    @Transient
//    @ManyToOne
//    @JoinColumn(name = "PROMOTION_STATUS")
//    private Promotion promotionStatus;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    private Role role;


    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
        if(!this.promotion.getPromotionDetails().contains(this)){
            this.promotion.getPromotionDetails().add(this);
        }

    }
}
