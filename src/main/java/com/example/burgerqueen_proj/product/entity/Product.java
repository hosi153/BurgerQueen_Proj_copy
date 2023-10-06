package com.example.burgerqueen_proj.product.entity;

import com.example.burgerqueen_proj.cart.entity.CartDetail;
import com.example.burgerqueen_proj.category.entity.Category;
import com.example.burgerqueen_proj.entity.BasicEntity;
import com.example.burgerqueen_proj.promotion.entity.PromotionDetails;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@AllArgsConstructor @Builder
@DynamicInsert @DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class Product extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long productId;

    private String productName;

    private int productPrice;

    @Builder.Default
    private int productCount=10; //default 10개

    private String productImage;

    //TODO : 할인가격을 entity에 넣어서 편하게 사용할 수 있도록 함
    @Transient
    private int discountPrice = setDiscountPrice();

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus = ProductStatus.PRODUCT_STOP;

    @ManyToOne
    @JoinColumn(name="CATEGORY_ID")
    private Category category;

    @Builder.Default
    @OneToMany(mappedBy = "product")
    private List<PromotionDetails> promotionDetails = new ArrayList<>();

    private int setDiscountPrice(){
        //상품에 연동된 promotiondetails 중, 단품할인+판매중인 데이터가 있는 경우 discoutprice 업데이트

        return 1;
    }
    public void setCategory(Category category){
        this.category = category;
        if(!this.category.getProducts().contains(this)){
            this.category.getProducts().add(this);
        }

    }


    @OneToMany(mappedBy = "product")
    private List<CartDetail> cartDetails = new ArrayList<>();

    public void addCartDetail(CartDetail cartDetail) {
        this.cartDetails.add(cartDetail);
        if (cartDetail.getProduct() != this) {
            cartDetail.addProduct(this);
        }
    }




    public enum ProductStatus{
        PRODUCT_ING(1, "판매중"),
        PRODUCT_STOP(2, "판매 중지"),
        PRDOUCT_EMPTY(3, "재고 없음");

        private int statusNumber;
        private String statusDescription;

        ProductStatus(int statusNumber, String statusDescription){
            this.statusNumber = statusNumber;
            this.statusDescription = statusDescription;
        }

    }



}
