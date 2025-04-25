package com.backendtest.similarproducts.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String productId) {
        super("Producto no encontrado: " + productId);
    }
}
