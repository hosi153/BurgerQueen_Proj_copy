package com.example.burgerqueen_proj.exception;

import lombok.Getter;

public enum ExceptionCode {

    PRODUCT_NOT_READY(404,"Product not Ready"),

    PRODUCT_NOT_FOUND(404, "Product not found"),
    USER_NOT_FOUND(404, "User not found"),
    ORDER_NOT_FOUND(404, "Order not found"),
    PRODUCT_EXISTS(409, "Product exists");

    @Getter
    private int status;

    @Getter
    private String message;

     ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
