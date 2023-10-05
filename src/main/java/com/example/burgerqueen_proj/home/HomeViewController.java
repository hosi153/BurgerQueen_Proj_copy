package com.example.burgerqueen_proj.home;

import com.example.burgerqueen_proj.category.dto.CategoryResponseDto;
import com.example.burgerqueen_proj.category.entity.Category;
import com.example.burgerqueen_proj.category.service.CategoryService;
import com.example.burgerqueen_proj.product.dto.ProductResponseDto;
import com.example.burgerqueen_proj.product.entity.Product;
import com.example.burgerqueen_proj.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeViewController {
    private final ProductService productService;
    private final CategoryService categoryService;

    //홈화면 출력 : header, fotter 및 주문가능한 상품정보 출력
    @GetMapping("/home")
    public String viewHome(Model model){
        List<CategoryResponseDto> categories = CategoryResponseDto.categoiresResponseDtos(categoryService.findAllCategory());
        List<ProductResponseDto> products = ProductResponseDto.productResponseDtos(productService.findAllProduct());

        model.addAttribute("categories", categories);
        model.addAttribute("products",products);
        return "home";
    }


}
