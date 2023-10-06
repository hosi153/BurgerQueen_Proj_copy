package com.example.burgerqueen_proj.cart.mapper;

import com.example.burgerqueen_proj.cart.dto.CartPatchDto;
import com.example.burgerqueen_proj.cart.dto.CartPostDto;
import com.example.burgerqueen_proj.cart.dto.CartProductResponseDto;
import com.example.burgerqueen_proj.cart.dto.CartResponseDto;
import com.example.burgerqueen_proj.cart.entity.Cart;
import com.example.burgerqueen_proj.cart.entity.CartDetail;
import com.example.burgerqueen_proj.member.entity.Member;
import com.example.burgerqueen_proj.product.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CartMapper {


    Cart cartPatchDtoToCart(CartPatchDto cartPatchDto);
    List<CartResponseDto> cartToCartResponseDtos(List<Cart> carts);
    Cart cartPostDtoToCart(CartPostDto cartPostDto);
//    default Cart cartPostDtoToCart(CartPostDto cartPostDto){
//        Cart cart = new Cart();
//        Member member = new Member();
//        member.setMemberId(cartPostDto.getMemberId());
//
//        List<CartDetail> cartDetails = cartPostDto.getCartDetails().stream()
//                .map(cartProductDto -> {
//                    CartDetail cartDetail = new CartDetail();
//                    Product product = new Product();
//                    product.setProductId(cartProductDto.getProduct().getProductId());
//                    cartDetail.addCart(cart);
//                    cartDetail.addProduct(product);
//                    cartDetail.setQuantity(cartProductDto.getQuantity());
//                    return cartDetail;
//                }).collect(Collectors.toList());
//        cart.setMember(member);
//        cart.setCartDetails(cartDetails);
//
//        return cart;
//    }

    CartResponseDto cartToCartResponseDto(Cart cart);


//    default CartResponseDto cartToCartResponseDto(Cart cart){
//        List<CartDetail> cartDetails = cart.getCartDetails();
//        CartResponseDto cartResponseDto = new CartResponseDto();
//        cartResponseDto.setCartId(cart.getCartId());
//        cartResponseDto.setMember(cart.getMember());
//        cartResponseDto.setCartProducts(cartProductToCartProductResponseDto(cartDetails));
//
//        return cartResponseDto;
//
//    }

    default List<CartProductResponseDto> cartProductToCartProductResponseDto ( List<CartDetail > cartDetails){
        return cartDetails
                .stream()
                .map(cartDetail -> CartProductResponseDto
                        .builder()
                        .productId(cartDetail.getProduct().getProductId())
                        .productName(cartDetail.getProduct().getProductName())
                        .price(cartDetail.getProduct().getProductPrice())
                        .quantity(cartDetail.getQuantity())
                        .build())
                .collect(Collectors.toList());
    }
}
