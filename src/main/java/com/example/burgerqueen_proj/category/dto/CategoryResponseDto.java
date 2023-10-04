package com.example.burgerqueen_proj.category.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryResponseDto {
    private long categoryId;
    private String categoryName;

}
