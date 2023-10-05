package com.example.burgerqueen_proj.product.controller;

import com.example.burgerqueen_proj.product.dto.ProductPatchDto;
import com.example.burgerqueen_proj.product.dto.ProductPostDto;
import com.example.burgerqueen_proj.product.dto.ProductResponseDto;
import com.example.burgerqueen_proj.product.entity.Product;
import com.example.burgerqueen_proj.product.mapper.ProductMapper;
import com.example.burgerqueen_proj.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;
    //private final ProductMapper productMapper;


    //READ : 상품 단건 조회
    @GetMapping("/{product-id}")
    public ResponseEntity findProduct(@PathVariable("product-id")long productId){
        return new ResponseEntity(new ProductResponseDto(productService.findProduct(productId)),HttpStatus.OK);
    }

    ////READ : 상품 전체조회
    // TODO : 페이징 처리 필요 : pageable
    @GetMapping("/all")
    public ResponseEntity allMenuByCategory(){
        //List<Product> productsbyCategory = productService.findAllProductByCategory();
        List<Product> products = productService.findAllProduct();
        return new ResponseEntity<>(ProductResponseDto.productResponseDtos(products), HttpStatus.OK);
    }

    //CUD는 관리자만 접근가능하도록 설정해야 함
    //CREATE : 상품 생성
    @PostMapping("/adm/new")
    public ResponseEntity createProduct(@RequestBody ProductPostDto productPostDto){
        Product newProduct = productService.createProduct(productPostDto.toEntity());
        return new ResponseEntity(new ProductResponseDto(newProduct),HttpStatus.CREATED);
    }

    //UPDATE : 상품 정보 업데이트
    @PatchMapping("/adm/{product-id}")
    public ResponseEntity updateProduct(@PathVariable("product-id")long productId, @RequestBody ProductPatchDto productPathDto){
        productPathDto.setProductId(productId);
        Product editProduct = productService.updateProduct(productPathDto.toEntity());
        return new ResponseEntity(new ProductResponseDto(editProduct), HttpStatus.OK);

    }


    // DELETE : 상품
    @DeleteMapping("/adm/{product-id}")
    public ResponseEntity deleteProduct(@PathVariable("product-id")long productId){
        productService.deleteProduct(productId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
