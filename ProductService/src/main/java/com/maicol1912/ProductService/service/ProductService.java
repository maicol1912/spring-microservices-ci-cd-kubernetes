package com.maicol1912.ProductService.service;

import com.maicol1912.ProductService.model.ProductRequest;
import com.maicol1912.ProductService.model.ProductResponse;

public interface ProductService {
    long addProduct(ProductRequest productRequest);
    ProductResponse getProductById(long productId);

    void reduceQuantity(long productId, long quantity);
}
