package com.example.burgerqueen_proj.exception;

import lombok.Getter;

public enum ExceptionCode {

    PRODUCT_NOT_READY(404,"Product not Ready"),
    PRODUCT_NOT_FOUND(404, "Product not found"),
    CATEGORY_NOT_FOUND(404, "Category not found"),
    CATEGORY_DUPLICATED(409, "Category already exists"),
    PRODUCT_EXISTS(409, "Product already exists"),

    USER_NOT_FOUND(404, "Member not found"),
    CART_NOT_FOUND(404, "Cart not found"),
    ORDER_NOT_FOUND(404, "Order not found");



    @Getter
    private int status;

    @Getter
    private String message;

     ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
