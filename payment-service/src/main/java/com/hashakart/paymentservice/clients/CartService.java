package com.hashakart.paymentservice.clients;

import com.hashakart.paymentservice.entities.Checkout;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cart-service")
public interface CartService {
    @GetMapping("/cart/checkout")
    Checkout getCheckout(@RequestParam("userId")Integer userId);
}
