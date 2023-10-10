package com.example.burgerqueen_proj.cart.controller;


import com.example.burgerqueen_proj.cart.dto.CartPatchDto;
import com.example.burgerqueen_proj.cart.dto.CartPostDto;
import com.example.burgerqueen_proj.cart.entity.Cart;
import com.example.burgerqueen_proj.cart.entity.CartProduct;
import com.example.burgerqueen_proj.cart.mapper.CartMapper;
import com.example.burgerqueen_proj.cart.service.CartService;
import com.example.burgerqueen_proj.member.service.MemberService;
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

        cart.setMember(memberService.findUser(cartPostDto.getMemberId()));
        CartProduct cartProduct = new CartProduct();
        cartProduct.setCart(cart);
        cartProduct.setProduct(productService.findProduct(1));
        System.out.println(productService.findProduct(1).getProductName());




        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @PatchMapping("/{cart-id}")
    public ResponseEntity patchCart(@PathVariable("cart-id")long cartId , @RequestBody CartPatchDto cartPatchDto){
        cartPatchDto.setCartId(cartId);
        Cart cart = cartService.updateCart(cartMapper.cartPatchDtoToCart(cartPatchDto));

        return new ResponseEntity<>(cart,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getCart( @RequestParam int page,
                                   @RequestParam int size){
        Page<Cart> cartPage = cartService.findCart(page,size);
        List<Cart> carts = cartPage.getContent();

        return new ResponseEntity<>(cartMapper.cartToCartResponseDtos(carts),HttpStatus.OK);

    }

    @DeleteMapping("/{cart-id}")
    public ResponseEntity deleteCart(@PathVariable("cart-id")long cartId){
        cartService.cancelCart(cartId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }





}
