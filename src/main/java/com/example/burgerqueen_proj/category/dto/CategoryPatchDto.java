package com.example.burgerqueen_proj.category.dto;

import com.example.burgerqueen_proj.category.entity.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
public class CategoryPatchDto {
    @Setter
    private long categoryId;
    private String categoryName;

    public Category toEntity(){
        return Category.builder()
                .categoryId(this.categoryId)
                .categoryName(this.categoryName)
                .build();
    }
}
