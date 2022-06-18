package com.hashkart.cartservice.controllers;

import com.hashkart.cartservice.services.CartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartServices cartServices;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllCart() {
        return new ResponseEntity<>(cartServices.getAllCarts(), HttpStatus.OK);
    }

    @PostMapping("/addItem")
    public ResponseEntity<Object> addItemToCart(@RequestParam("userId") Integer userId, @RequestParam("productId") Integer productId, @RequestParam("quantity") Integer quantity) {
        cartServices.addItem(userId, productId, quantity);
        return ResponseEntity.ok("Added");
    }
}
