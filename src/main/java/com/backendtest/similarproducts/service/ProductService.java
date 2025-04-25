package com.backendtest.similarproducts.service;

import com.backendtest.similarproducts.model.ProductDetail;

import java.util.List;

public interface ProductService {
    List<ProductDetail> getSimilarProducts(String productId);
}
