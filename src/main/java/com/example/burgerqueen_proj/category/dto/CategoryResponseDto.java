package com.example.burgerqueen_proj.category.dto;

import com.example.burgerqueen_proj.category.entity.Category;
import com.example.burgerqueen_proj.product.dto.ProductResponseDto;
import com.example.burgerqueen_proj.product.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class CategoryResponseDto {
    private long categoryId;
    private String categoryName;
    private int countPorducts;

    public CategoryResponseDto(Category category) {
        this.categoryId = category.getCategoryId();
        this.categoryName = category.getCategoryName();
        this.countPorducts = category.getCountProduct();
    }

    public static List<CategoryResponseDto> categoiresResponseDtos(List<Category> categories) {
        List<CategoryResponseDto> dtos = new ArrayList(categories.size());
        for (Category category : categories){
            dtos.add(new CategoryResponseDto(category));
        }
        return dtos;
    }
}
