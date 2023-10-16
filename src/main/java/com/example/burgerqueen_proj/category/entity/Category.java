package com.example.burgerqueen_proj.category.entity;

import com.example.burgerqueen_proj.entity.BasicEntity;
import com.example.burgerqueen_proj.product.entity.Product;
import com.example.burgerqueen_proj.promotion.entity.Promotion;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor @Builder
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Category extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryId;

    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();;

    @Transient
    private int countProduct;

    @OneToOne
    @JoinColumn(name="promotionId")
    private Promotion promotion;


    public int getCountProduct(){
        this.countProduct=0;
        System.out.println(this.products);
        if(this.products!=null) {
            this.countProduct = this.products.toArray().length;
        }
        return countProduct;
    }
    public void setProduct(Product product){
        this.products.add(product);
        if(product.getCategory() !=this){
            product.setCategory(this);
        }
    }

    public Category updateCategory(Category category){
        this.categoryName=category.getCategoryName();
        return this;
    }


}
