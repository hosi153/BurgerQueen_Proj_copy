package com.example.burgerqueen_proj.cart.service;


import com.example.burgerqueen_proj.cart.entity.Cart;
import com.example.burgerqueen_proj.cart.entity.CartProduct;
import com.example.burgerqueen_proj.cart.repository.CartRepository;
import com.example.burgerqueen_proj.exception.BusinessLogicException;
import com.example.burgerqueen_proj.exception.ExceptionCode;
import com.example.burgerqueen_proj.member.service.MemberService;
import com.example.burgerqueen_proj.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {


    private final MemberService memberService;
    private final CartRepository cartRepository;
    private final ProductService productService;


    public Cart createCart(Cart cart){
        verifyCart(cart);
        Cart savedCart = saveCart(cart);

        return savedCart;
    }


    public Cart updateCart(Cart cart){
        Cart findCart = findVerifiedCart(cart.getCartId());

        Optional.ofNullable(cart.getTotalCount()).ifPresent(totalCount -> findCart.setTotalCount(totalCount));
        return cartRepository.save(findCart);
    }

    public Cart findcart(long cartId){
        Cart findCart = findVerifiedCart(cartId);
        return findCart;
    }

    public Page<Cart> findCarts(int page, int size){
        return cartRepository.findAll(PageRequest.of(page,size, Sort.by("cartId").descending()));
    }

    public void cancelCart(long cartId){ ////////// 수정수정수정 수정
        Cart findCart = findVerifiedCart(cartId);
        cartRepository.delete(findCart);
    }

    private Cart findVerifiedCart   (long cartId) {
        Optional<Cart> optionalOrder = cartRepository.findById(cartId);
        Cart findOrder =
                optionalOrder.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.CART_NOT_FOUND));
        return findOrder;
    }

    private void verifyCart(Cart cart) {
        // 회원이 존재하는지 확인
        memberService.findVerifiedUser(cart.getMember().getMemberId());

        // 커피가 존재하는지 확인
        cart.getCartProducts().stream()
                .forEach(cartProduct ->  productService.findVerifyProduct(cartProduct.getProduct().getProductId()));

    }

    private Cart saveCart(Cart cart) {
//        if (memberService.findVerifiedUser(cart.getMember().getMemberId())!=null){
//            return cartRepository.findById(cart.getCartId()).orElseThrow();
//        }
        //프로덕트 세팅
        //카트프로덕트 세팅
        int totalPrice = 0;
        for(int i=0;i<cart.getCartProducts().size();i++){
            cart.getCartProducts().get(i).setProduct(productService.findProduct(cart.getCartProducts().get(i).getProduct().getProductId()));
            totalPrice+=cart.getCartProducts().get(i).getProduct().getProductPrice()*cart.getCartProducts().get(i).getQuantity();
        }
        cart.setTotalPrice(totalPrice);



        return cartRepository.save(cart);
    }

}
