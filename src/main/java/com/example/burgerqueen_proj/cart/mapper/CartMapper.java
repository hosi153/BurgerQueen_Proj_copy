package com.example.burgerqueen_proj.cart.mapper;

import com.example.burgerqueen_proj.cart.dto.*;
import com.example.burgerqueen_proj.cart.entity.Cart;
import com.example.burgerqueen_proj.cart.entity.CartProduct;
import com.example.burgerqueen_proj.member.entity.Member;
import com.example.burgerqueen_proj.product.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CartMapper {


    Cart cartPatchDtoToCart(CartPatchDto cartPatchDto);

    List<CartResponseDto> cartToCartResponseDtos(List<Cart> carts);

//        Cart cartPostDtoToCart(CartPostDto cartPostDto);
    default Cart cartPostDtoToCart(CartPostDto cartPostDto) {
        Cart cart = new Cart();
        Member member = new Member();
        member.setMemberId(cartPostDto.getMemberId());

        List<CartProduct> cartProducts = cartPostDto.getCartProducts().stream()
                .map(cartProductDto ->  {
                    CartProduct cartProduct = new CartProduct();
                    Product product = new Product();
                    product.setProductId(cartProductDto.getProductId());
                    cartProduct.addCart(cart);
                    cartProduct.addProduct(product);
                    cartProduct.setQuantity(cartProductDto.getQuantity());
                    return cartProduct;
                }).collect(Collectors.toList());
        cart.setMember(member);
        cart.setCartProducts(cartProducts);

        return cart;
    }


//    CartResponseDto cartToCartResponseDto(Cart cart);


    default CartResponseDto cartToCartResponseDto(Cart cart){
        List<CartProduct> cartProducts = cart.getCartProducts();
        CartResponseDto cartResponseDto = new CartResponseDto();
        cartResponseDto.setCartId(cart.getCartId());
        cartResponseDto.setMember(cart.getMember());
        cartResponseDto.setCartProducts(cartProductToCartProductResponseDto(cartProducts));

        return cartResponseDto;

    }

    default List<CartProductResponseDto> cartProductToCartProductResponseDto(List<CartProduct> cartProducts) {
        return cartProducts
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
