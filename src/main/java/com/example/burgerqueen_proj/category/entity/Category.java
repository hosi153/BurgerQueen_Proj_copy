package com.example.burgerqueen_proj.category.entity;

import com.example.burgerqueen_proj.product.entity.Product;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryId;

    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();




}
