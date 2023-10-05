package com.example.burgerqueen_proj.product.mapper;

import com.example.burgerqueen_proj.product.dto.ProductPostDto;
import com.example.burgerqueen_proj.product.entity.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-04T16:34:02+0900",
    comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 17.0.8 (JetBrains s.r.o.)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product productPostDtoToProduct(ProductPostDto productPostDto) {
        if ( productPostDto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.productName( productPostDto.getProductName() );
        product.productPrice( productPostDto.getProductPrice() );
        product.productCount( productPostDto.getProductCount() );
        product.productImage( productPostDto.getProductImage() );
        product.category( productPostDto.getCategory() );

        return product.build();
    }
}
