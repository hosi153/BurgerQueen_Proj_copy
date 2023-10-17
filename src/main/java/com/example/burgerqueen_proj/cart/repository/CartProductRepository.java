package com.example.burgerqueen_proj.cart.repository;

import com.example.burgerqueen_proj.cart.entity.Cart;
import com.example.burgerqueen_proj.cart.entity.CartProduct;
import com.example.burgerqueen_proj.member.entity.Member;
import com.example.burgerqueen_proj.product.entity.Product;
import com.example.burgerqueen_proj.promotion.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartProductRepository extends JpaRepository<CartProduct,Long> {

    CartProduct findByProduct(Product product);

    CartProduct findByCartAndProduct(Cart cart, Product product);
    CartProduct findByCart(Cart cart);
    void deleteAllByCart(Cart cart);
    void deleteByCart(Cart cart);



}
