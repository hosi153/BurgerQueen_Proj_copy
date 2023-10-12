package com.example.burgerqueen_proj.cart.repository;

import com.example.burgerqueen_proj.cart.entity.Cart;
import com.example.burgerqueen_proj.cart.entity.CartProduct;
import com.example.burgerqueen_proj.member.entity.Member;
import com.example.burgerqueen_proj.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartProductRepository extends JpaRepository<CartProduct,Long> {

    CartProduct findByProduct(Product product);
    void deleteAllByCart(Cart cart);

}
