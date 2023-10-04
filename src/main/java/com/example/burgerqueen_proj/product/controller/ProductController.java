package com.example.burgerqueen_proj.product.controller;

import com.example.burgerqueen_proj.product.dto.ProductResponseDto;
import com.example.burgerqueen_proj.product.entity.Product;
import com.example.burgerqueen_proj.product.mapper.ProductMapper;
import com.example.burgerqueen_proj.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;


    @GetMapping("/{product-id}")
    public ResponseEntity findProduct(@PathVariable("product-id")long productId){
        return new ResponseEntity(new ProductResponseDto(productService.findProduct(productId)),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity allMenuByCategory(){
        // TODO : 페이징 처리 필요 : pageable
        List<Product> productsbyCategory = productService.findAllProductByCategory();
        return new ResponseEntity<>(ProductResponseDto.productResponseDtos(productsbyCategory), HttpStatus.OK);
    }

    // 수정


    // 삭제

}
