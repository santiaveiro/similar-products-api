package com.backendtest.similarproducts.controller;

import com.backendtest.similarproducts.model.ProductDetail;
import com.backendtest.similarproducts.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{productId}/similar")
    public ResponseEntity<List<ProductDetail>> getSimilarProducts(@PathVariable String productId) {
        List<ProductDetail> similarProducts = productService.getSimilarProducts(productId);
        return ResponseEntity.ok(similarProducts);
    }
}
