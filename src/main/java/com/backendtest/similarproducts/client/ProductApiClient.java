package com.backendtest.similarproducts.client;

import com.backendtest.similarproducts.model.ProductDetail;

import java.util.List;

public interface ProductApiClient {
    List<String> getSimilarProductIds(String productId);
    ProductDetail getProductDetail(String productId);
}
