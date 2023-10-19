package com.example.burgerqueen_proj.cart.controller;


import com.example.burgerqueen_proj.cart.dto.CartPatchDto;
import com.example.burgerqueen_proj.cart.dto.CartPostDto;
import com.example.burgerqueen_proj.cart.dto.CartProductDto;
import com.example.burgerqueen_proj.cart.dto.CartProductPatchDto;
import com.example.burgerqueen_proj.cart.entity.Cart;
import com.example.burgerqueen_proj.cart.entity.CartProduct;
import com.example.burgerqueen_proj.cart.mapper.CartMapper;
import com.example.burgerqueen_proj.cart.service.CartService;
import com.example.burgerqueen_proj.member.service.MemberService;
import com.example.burgerqueen_proj.product.entity.Product;
import com.example.burgerqueen_proj.product.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.channels.Pipe;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@Api(tags="장바구니 관리 API")
//@Api(value = "CartController" , tags = "장바구니 관리 API")


public class CartController {


    private final CartService cartService;
    private final CartMapper cartMapper;
//    private final MemberService memberService;
//    private final ProductService productService;

    @PostMapping
//    @ApiOperation(value = "장바구니 생성")
    public ResponseEntity postCart(@RequestBody CartPostDto cartPostDto){
        Cart cart = cartService.createCart(cartMapper.cartPostDtoToCart(cartPostDto));
        return new ResponseEntity<>(cartMapper.cartToCartResponseDto(cart), HttpStatus.CREATED);
    }

    @PatchMapping("/{cart-id}")
//    @ApiOperation(value = "장바구니 수정(업데이트)")
    public ResponseEntity patchCart(@PathVariable("cart-id")long cartId , @RequestBody CartPatchDto cartPatchDto){
        cartPatchDto.setCartId(cartId);
        Cart cart = cartService.updateCart(cartMapper.cartPatchDtoToCart(cartPatchDto));

        return new ResponseEntity<>(cartMapper.cartToCartResponseDto(cart),HttpStatus.OK);

    }

    @GetMapping("/{cart-id}")
//    @ApiOperation(value = "장바구니 단건 조회")
    public ResponseEntity getCart(@PathVariable("cart-id")long cartId){
        Cart cart = cartService.findcart(cartId);
       return new ResponseEntity(cartMapper.cartToCartResponseDto(cart),HttpStatus.OK);


    }

    @GetMapping
//    @ApiOperation(value = "장바구니 전체 조회")
    public ResponseEntity getCarts( @RequestParam int page,
                                   @RequestParam int size){
        Page<Cart> cartPage = cartService.findCarts(page,size);
        List<Cart> carts = cartPage.getContent();

        return new ResponseEntity<>(cartMapper.cartToCartResponseDtos(carts),HttpStatus.OK);

    }


    @DeleteMapping("/{cart-id}/{product-id}")
//    @ApiOperation(value = "장바구니 내 상품 단건 삭제")
    public ResponseEntity deleteCart(@PathVariable("cart-id")long cartId,@PathVariable("product-id") long prductId ){
        cartService.deleteCartProduct(cartId, prductId);
        return new ResponseEntity(HttpStatus.OK);
    }


//    @DeleteMapping("/{cart-id}/{product-id}") //단건 삭제
//    public ResponseEntity deleteCartProduct(@PathVariable("cart-id")long cartId,@PathVariable("product-id")long productId){
//
//        cartService.deleteCartProduct(cartId,productId);
//
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
////
    @DeleteMapping("/{cart-id}") //장바구니 비우기
//    @ApiOperation(value = "장바구니 비우기")
    public ResponseEntity dCart(@PathVariable("cart-id")long cartId){
        cartService.clearCartProduct(cartId);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
