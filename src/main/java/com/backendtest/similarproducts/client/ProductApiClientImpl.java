package com.backendtest.similarproducts.client;

import com.backendtest.similarproducts.exception.ProductNotFoundException;
import com.backendtest.similarproducts.model.ProductDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductApiClientImpl implements ProductApiClient {

    private static final String BASE_URL = "http://localhost:3001";
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<String> getSimilarProductIds(String productId) {
        try {
            String[] ids = restTemplate.getForObject(
                    BASE_URL + "/product/" + productId + "/similarids",
                    String[].class
            );
            return Arrays.asList(ids);
        } catch (HttpClientErrorException.NotFound e) {
            throw new ProductNotFoundException(productId);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener productos similares para el ID: " + productId, e);
        }
    }

    @Override
    public ProductDetail getProductDetail(String productId) {
        try {
            ResponseEntity<ProductDetail> response = restTemplate.getForEntity(
                    BASE_URL + "/product/" + productId,
                    ProductDetail.class
            );
            return response.getBody();
        } catch (HttpClientErrorException.NotFound e) {
            throw new ProductNotFoundException(productId);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el detalle del producto: " + productId, e);
        }
    }
}
