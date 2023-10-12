package com.example.burgerqueen_proj.cart.controller;


import com.example.burgerqueen_proj.cart.dto.CartPatchDto;
import com.example.burgerqueen_proj.cart.dto.CartPostDto;
import com.example.burgerqueen_proj.cart.entity.Cart;
import com.example.burgerqueen_proj.cart.entity.CartProduct;
import com.example.burgerqueen_proj.cart.mapper.CartMapper;
import com.example.burgerqueen_proj.cart.service.CartService;
import com.example.burgerqueen_proj.member.service.MemberService;
import com.example.burgerqueen_proj.product.entity.Product;
import com.example.burgerqueen_proj.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.channels.Pipe;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {


    private final CartService cartService;
    private final CartMapper cartMapper;
    private final MemberService memberService;
    private final ProductService productService;

    @PostMapping
    public ResponseEntity postCart(@RequestBody CartPostDto cartPostDto){
        Cart cart = cartService.createCart(cartMapper.cartPostDtoToCart(cartPostDto));

        cart.setMember(memberService.findMember(cartPostDto.getMemberId()));


        for (CartProduct cartProduct : cart.getCartProducts()){
            System.out.println(cartProduct.getProduct().getProductName());
            System.out.println(cartProduct.getProduct().getProductPrice()+"원");
        }





        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @PatchMapping("/{cart-id}")
    public ResponseEntity patchCart(@PathVariable("cart-id")long cartId , @RequestBody CartPatchDto cartPatchDto){
        cartPatchDto.setCartId(cartId);
        Cart cart = cartService.updateCart(cartMapper.cartPatchDtoToCart(cartPatchDto));

        return new ResponseEntity<>(cart,HttpStatus.OK);

    }

    @GetMapping("/{cart-id}")
    public ResponseEntity getCart(@PathVariable("cart-id")long cartId){
        Cart cart = cartService.findcart(cartId);
       return new ResponseEntity(cartMapper.cartToCartResponseDto(cart),HttpStatus.OK);


    }

    @GetMapping
    public ResponseEntity getCarts( @RequestParam int page,
                                   @RequestParam int size){
        Page<Cart> cartPage = cartService.findCarts(page,size);
        List<Cart> carts = cartPage.getContent();

        return new ResponseEntity<>(cartMapper.cartToCartResponseDtos(carts),HttpStatus.OK);

    }

    @DeleteMapping("/{cart-id}")
    public ResponseEntity deleteCart(@PathVariable("cart-id")long cartId){
        cartService.cancelCart(cartId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }





}
