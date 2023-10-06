package com.example.burgerqueen_proj.cart.service;


import com.example.burgerqueen_proj.cart.entity.Cart;
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
        Cart savedCart = saveCart(cart);

        return savedCart;
    }


    public Cart updateCart(Cart cart){
        Cart findCart = findVerifiedCart(cart.getCartId());

//        Optional.ofNullable(cart.getTotalCount()).ifPresent(totalCount -> findCart.setTotalCount(totalCount));
        return cartRepository.save(findCart);
    }

    public Page<Cart> findCart(int page, int size){
        return cartRepository.findAll(PageRequest.of(page,size, Sort.by("cartId").descending()));
    }

    public void cancelCart(long cartId){
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
//        cart.getCartDetails().stream()
//                .forEach(cartProduct -> productService.
//                        findVerifyProduct(cartProduct.getProduct().getProductId()));
    }

    private Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

}
