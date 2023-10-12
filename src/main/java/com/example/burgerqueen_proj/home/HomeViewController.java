package com.example.burgerqueen_proj.home;

import com.example.burgerqueen_proj.cart.dto.CartProductResponseDto;
import com.example.burgerqueen_proj.cart.dto.CartResponseDto;
import com.example.burgerqueen_proj.cart.entity.Cart;
import com.example.burgerqueen_proj.cart.mapper.CartMapper;
import com.example.burgerqueen_proj.cart.repository.CartRepository;
import com.example.burgerqueen_proj.cart.service.CartService;
import com.example.burgerqueen_proj.category.dto.CategoryResponseDto;
import com.example.burgerqueen_proj.category.entity.Category;
import com.example.burgerqueen_proj.category.service.CategoryService;
import com.example.burgerqueen_proj.member.dto.MemberResponseDto;
import com.example.burgerqueen_proj.member.entity.Member;
import com.example.burgerqueen_proj.member.service.MemberService;
import com.example.burgerqueen_proj.product.dto.ProductResponseDto;
import com.example.burgerqueen_proj.product.entity.Product;
import com.example.burgerqueen_proj.product.service.ProductService;
import com.example.burgerqueen_proj.promotion.dto.PromotionResponseDto;
import com.example.burgerqueen_proj.promotion.service.PromotionService;
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
    private final PromotionService promotionService;
    private final MemberService memberService;

    private final CartMapper cartMapper;
    private final CartService cartService;
    private final CartRepository cartRepository;

    //홈화면 출력 : header, fotter 및 주문가능한 상품정보 출력
    @GetMapping("/home")
    public String viewHome(Model model){
        List<CategoryResponseDto> categories = CategoryResponseDto.categoiresResponseDtos(categoryService.findAllCategoryHaveProduct());
        List<ProductResponseDto> products = ProductResponseDto.productResponseDtos(productService.findAllProduct());
        List<PromotionResponseDto> promotions = PromotionResponseDto.promotionResponseDtos(promotionService.getActivePromotions());

        Member member = memberService.findMember(1L);
        MemberResponseDto memberDto = new MemberResponseDto(member);
        CartResponseDto cart = cartMapper.cartToCartResponseDto(cartService.findCartByMember(member));

        model.addAttribute("cartId", cart.getCartId());
        model.addAttribute("promotions", promotions);
        model.addAttribute("categories", categories);
        model.addAttribute("products",products);
        model.addAttribute("member",memberDto);
        model.addAttribute("cart", cart.getCartProducts());
        return "home";
    }



    @GetMapping("/cart")
    public String viewCart(Model model){

        Member member = memberService.findMember(1L);
        CartResponseDto cart = cartMapper.cartToCartResponseDto(cartService.findCartByMember(member));

        model.addAttribute("cart",cart);

        return "cart";
    }

    @GetMapping("/myPage")
    public String viewMyPage(Model model){
        MemberResponseDto member = new MemberResponseDto(memberService.findMember(1L));

        model.addAttribute("member",member);

        return "myPage";
    }


}
