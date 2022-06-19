package com.hashkart.cartservice.clients;

import com.hashkart.cartservice.entities.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("product-service")
public interface ProductService {

    @GetMapping("/products/{id}")
    Product getProductById(@PathVariable("id") Integer productId);
}
