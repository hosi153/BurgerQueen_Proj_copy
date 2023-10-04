package com.example.burgerqueen_proj.product.mapper;

import com.example.burgerqueen_proj.product.dto.ProductPostDto;
import com.example.burgerqueen_proj.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {


    Product productPostDtoToProduct(ProductPostDto productPostDto);

}
