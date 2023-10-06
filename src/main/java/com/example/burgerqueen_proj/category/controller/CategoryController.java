package com.example.burgerqueen_proj.category.controller;

import com.example.burgerqueen_proj.category.dto.CategoryPatchDto;
import com.example.burgerqueen_proj.category.dto.CategoryPostDto;
import com.example.burgerqueen_proj.category.dto.CategoryResponseDto;
import com.example.burgerqueen_proj.category.entity.Category;
import com.example.burgerqueen_proj.category.repository.CategoryRepository;
import com.example.burgerqueen_proj.category.service.CategoryService;
import com.example.burgerqueen_proj.product.dto.ProductResponseDto;
import com.example.burgerqueen_proj.product.entity.Product;
import com.example.burgerqueen_proj.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ctg")
// 23.10.05 JY
public class CategoryController {
    private final CategoryService categoryService;
    private final ProductService productService;

    //READ : 카테고리 조회
    @GetMapping
    public ResponseEntity findCategory(){
        return new ResponseEntity<>(CategoryResponseDto.categoiresResponseDtos(categoryService.findAllCategory()), HttpStatus.OK);
    }

    //READ : 카테고리ID별 상품조회
    @GetMapping("/{ctg-id}")
    public ResponseEntity findProductByCtegory(@PathVariable("ctg-id")long categoryId){
        List<Product> products = productService.findAllProductByCategory(categoryId);
        return new ResponseEntity(ProductResponseDto.productResponseDtos(products), HttpStatus.OK);
    }

    //관리자기능(CUD)
    //CREATE : 카테고리 생성
    @PostMapping("/adm/new")
    public ResponseEntity createCategory(@RequestBody CategoryPostDto categoryPostDto){
        Category newCategory = categoryService.createCategory(categoryPostDto.toEntity());
        return new ResponseEntity(new CategoryResponseDto(newCategory), HttpStatus.CREATED);
    }


    //UPDATE : 카테고리 수정
    @PatchMapping("/adm/{ctg-id}")
    public ResponseEntity updateCategory(@PathVariable("ctg-id")long categoryId, @RequestBody CategoryPatchDto categoryPatchDto){
        categoryPatchDto.setCategoryId(categoryId);
        Category updateCategory = categoryService.updateCategory(categoryPatchDto.toEntity());
        return new ResponseEntity(new CategoryResponseDto(updateCategory), HttpStatus.OK);
    }

    //DELETE : 카테고리 삭제
    @DeleteMapping("/adm/{ctg-id}")
    public ResponseEntity deleteCategory(@PathVariable("ctg-id")long categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
