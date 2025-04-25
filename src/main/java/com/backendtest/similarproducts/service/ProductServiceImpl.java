package com.backendtest.similarproducts.service;

import com.backendtest.similarproducts.client.ProductApiClient;
import com.backendtest.similarproducts.exception.ProductNotFoundException;
import com.backendtest.similarproducts.model.ProductDetail;
import com.backendtest.similarproducts.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class ProductServiceImpl implements ProductService {

    private final ProductApiClient productApiClient;

    public ProductServiceImpl(ProductApiClient productApiClient) {
        this.productApiClient = productApiClient;
    }

    @Override
    public List<ProductDetail> getSimilarProducts(String productId) {
        List<String> similarIds = productApiClient.getSimilarProductIds(productId);
        List<ProductDetail> similarProducts = new ArrayList<>();

        for (String id : similarIds) {
            ProductDetail detail = productApiClient.getProductDetail(id);
            if (detail == null) {
                throw new ProductNotFoundException(id);
            }
            similarProducts.add(detail);
        }

        return similarProducts;
    }
}
