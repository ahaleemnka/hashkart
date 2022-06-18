package com.hashkart.productservice.exception;

public class CategoryNotFound extends RuntimeException {
    public CategoryNotFound(String message) {
        super("Category not found : "+message );
    }
}
