package com.example.burgerqueen_proj.exception;

import lombok.Getter;

public enum ExceptionCode {

    PRODUCT_NOT_READY(404,"Product not Ready"),

    PRODUCT_NOT_FOUND(404, "Product not found"),
    CATEGORY_NOT_FOUND(404, "Category not found"),
    PRODUCT_EXISTS(409, "Product already exists");

    @Getter
    private int status;

    @Getter
    private String message;

     ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
