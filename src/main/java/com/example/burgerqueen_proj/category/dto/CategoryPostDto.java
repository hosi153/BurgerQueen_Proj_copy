package com.example.burgerqueen_proj.category.dto;

import com.example.burgerqueen_proj.category.entity.Category;
import lombok.Getter;

@Getter
public class CategoryPostDto {
    private String categoryName;

    public Category toEntity(){
        return Category.builder()
                .categoryName(this.categoryName)
                .build();
    }
}
