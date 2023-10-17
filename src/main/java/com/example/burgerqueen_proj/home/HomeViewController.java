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
import com.example.burgerqueen_proj.delivery.dto.DeliveryResponseDto;
import com.example.burgerqueen_proj.delivery.mapper.DeliveryMapper;
import com.example.burgerqueen_proj.delivery.repository.DeliveryRepository;
import com.example.burgerqueen_proj.delivery.service.DeliveryService;
import com.example.burgerqueen_proj.member.dto.MemberResponseDto;
import com.example.burgerqueen_proj.member.entity.Member;
import com.example.burgerqueen_proj.member.service.MemberService;
import com.example.burgerqueen_proj.order.dto.OrderResponseDto;
import com.example.burgerqueen_proj.order.entity.Order;
import com.example.burgerqueen_proj.order.mapper.OrderMapper;
import com.example.burgerqueen_proj.order.service.OrderService;
import com.example.burgerqueen_proj.product.dto.ProductResponseDto;
import com.example.burgerqueen_proj.product.entity.Product;
import com.example.burgerqueen_proj.product.service.ProductService;
import com.example.burgerqueen_proj.promotion.dto.PromotionResponseDto;
import com.example.burgerqueen_proj.promotion.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeViewController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final PromotionService promotionService;
    private final MemberService memberService;
    private final OrderService orderService;
    private final DeliveryService deliveryService;

    private final CartMapper cartMapper;
    private final CartService cartService;
    private final CartRepository cartRepository;
    private final DeliveryRepository deliveryRepository;

    private final OrderMapper orderMapper;
    private final DeliveryMapper deliveryMapper;


    //홈화면 출력 : header, fotter 및 주문가능한 상품정보 출력
    @GetMapping("/home")
    public String viewHome(Model model){
        List<CategoryResponseDto> categories = CategoryResponseDto.categoiresResponseDtos(categoryService.findAllCategoryHaveProduct());
        List<ProductResponseDto> products = ProductResponseDto.productResponseDtos(productService.findAllProduct());
        List<PromotionResponseDto> promotions = PromotionResponseDto.promotionResponseDtos(promotionService.getActivePromotions());

        Member member = memberService.findMemberByEmail(getUserInfo());
        CartResponseDto cart = cartMapper.cartToCartResponseDto(cartService.findCartByMember(member));

        model.addAttribute("cartId", cart.getCartId());
        model.addAttribute("promotions", promotions);
        model.addAttribute("categories", categories);
        model.addAttribute("products",products);
        model.addAttribute("member",new MemberResponseDto(member));
        model.addAttribute("cart", cart.getCartProducts());
        return "home";
    }



    @GetMapping("/cart")
    public String viewCart(Model model){

        Member member = memberService.findMemberByEmail(getUserInfo());
        CartResponseDto cart = cartMapper.cartToCartResponseDto(cartService.findCartByMember(member));


        model.addAttribute("cart",cart);
        model.addAttribute("member",new MemberResponseDto(member));

        return "cart";
    }

    @GetMapping("/myPage")
    public String viewMyPage(Model model){
        Member findMember = memberService.findMemberByEmail(getUserInfo());
        MemberResponseDto member = new MemberResponseDto(findMember);

        List<DeliveryResponseDto> delivery = deliveryMapper.deliveryToDeliveryResponseDtos(deliveryService.findDeliveries());


        model.addAttribute("member",member);
        model.addAttribute("delivery",delivery);



        return "myPage";
    }
    @GetMapping("/myPage/delivery/{delivery-id}")
    public String viewMyPage(Model model, @PathVariable("delivery-id")long deliveryId){


        Member findMember = memberService.findMemberByEmail(getUserInfo());
        MemberResponseDto member = new MemberResponseDto(findMember);


        DeliveryResponseDto delivery = deliveryMapper.deliveryToDeliveryResponseDto(deliveryService.findDelivery(deliveryId));
        OrderResponseDto order = orderMapper.orderToOrderResponseDto(orderService.findOrder(delivery.getOrderId()));

        model.addAttribute("member",member);
        model.addAttribute("delivery",delivery);
        model.addAttribute("order",order);



        return "orderDetail";
    }

    @GetMapping("/editUser")
    public String editMyPage(Model model){
        MemberResponseDto member = new MemberResponseDto(memberService.findMemberByEmail(getUserInfo()));

        model.addAttribute("member",member);

        return "editUser";
    }

    @GetMapping("/login")
    public String loginPage(){

        return "login";
    }
    @GetMapping("/join")
    public String joinPage(){

        return "join";
    }



    @GetMapping("/empty-cart")
    public String viewEmptyCart(Model model){

        Member member = memberService.findMemberByEmail(getUserInfo());
        CartResponseDto cart = cartMapper.cartToCartResponseDto(cartService.findCartByMember(member));


        model.addAttribute("cart",cart);
        model.addAttribute("member",new MemberResponseDto(member));

        return "empty-cart";
    }

    @GetMapping("/order")
    public String viewOrder(Model model){

        Member member = memberService.findMemberByEmail(getUserInfo());

        OrderResponseDto order = orderMapper.orderToOrderResponseDto(orderService.findOrder(1));

        model.addAttribute("order",order);
        model.addAttribute("member",new MemberResponseDto(member));

        return "order";
    }

    private String getUserInfo(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Member member = (Member) principal;
        String email = ((Member) principal).getUsername();

        return email;

    }


}
