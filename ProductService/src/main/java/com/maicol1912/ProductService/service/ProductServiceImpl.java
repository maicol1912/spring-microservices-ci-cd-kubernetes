package com.maicol1912.ProductService.service;

import com.maicol1912.ProductService.entity.Product;
import com.maicol1912.ProductService.exception.ProductServiceCustomException;
import com.maicol1912.ProductService.model.ProductRequest;
import com.maicol1912.ProductService.model.ProductResponse;
import com.maicol1912.ProductService.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;

    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("Adding Product...");
        Product product
                = Product.builder()
                .productName(productRequest.getName())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product created");
        return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(long productId) {
        log.info("Get the product for productId: {}",productId);
        Product product =
                productRepository.findById(productId)
                        .orElseThrow(
                                ()-> new ProductServiceCustomException("Product with id given not exists","PRODUCT_NOT_FOUND"));

        ProductResponse productResponse
                = new ProductResponse();

        BeanUtils.copyProperties(product,productResponse);

        return productResponse;

    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reduce Quantity {} for Id: {}",quantity,productId);

        Product product =
                productRepository.findById(productId)
                        .orElseThrow(()->new ProductServiceCustomException(
                                "Product with given Id not found",
                                "PRODUCT_NOT_FOUND"
                        ));

        if(product.getQuantity()<quantity){
            throw new ProductServiceCustomException(
                    "Product does not have sufficient Quantity",
                    "INSUFFICIENT_QUANTITY"
            );
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);

        log.info("Product Quantity Updated Successfully");
    }
}
