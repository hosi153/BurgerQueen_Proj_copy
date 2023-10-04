package com.example.burgerqueen_proj.product.dto;

import com.example.burgerqueen_proj.product.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;



@Getter
@Setter
public class ProductResponseDto {
    private long productId;
    private String productName;
    private int productPrice;
    private String productImage;
    private String category;

    public ProductResponseDto(Product product){
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.productPrice = product.getProductPrice();
        this.productImage = product.getProductImage();
        this.category = product.getCategory().getCategoryName();
    }
    public static List<ProductResponseDto> productResponseDtos(List<Product> products){
        List<ProductResponseDto> dtos = new ArrayList(products.size());
        for (Product product : products){
            dtos.add(new ProductResponseDto(product));
        }
        return dtos;

    }
}
