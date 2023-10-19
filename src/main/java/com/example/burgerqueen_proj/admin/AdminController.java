package com.example.burgerqueen_proj.admin;

import com.example.burgerqueen_proj.product.dto.ProductResponseDto;
import com.example.burgerqueen_proj.product.entity.Product;
import com.example.burgerqueen_proj.product.service.ProductService;
import com.example.burgerqueen_proj.security.JwtTokenProvider;
import com.example.burgerqueen_proj.security.TokenInfo;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags="관리자만 접근 가능한 API", description="Spring Security 권한관리 확인을 위함")
@RestController
@RequiredArgsConstructor
public class AdminController {
    private final JwtTokenProvider jwtTokenProvider;
    private final ProductService productService;

    @GetMapping("/api/admin/authenticated")
    public String getAuthentication(){
        return "is Authenticated";
    }

    @GetMapping("/api/admin/token")
    public String getToken(){
        TokenInfo token = jwtTokenProvider.generateToken(SecurityContextHolder.getContext().getAuthentication());
        return token.getAccessToken();
    }

    @GetMapping("/api/admin/tokenTest")
    public ResponseEntity tokenTest(){

        List<Product> products = productService.findAllProduct();
        return new ResponseEntity<>(ProductResponseDto.productResponseDtos(products), HttpStatus.OK);

    }

}
