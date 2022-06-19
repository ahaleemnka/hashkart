package com.hashkart.cartservice.controllers;

import com.hashkart.cartservice.entities.AddItemRequest;
import com.hashkart.cartservice.models.Cart;
import com.hashkart.cartservice.models.Checkout;
import com.hashkart.cartservice.services.CartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public ResponseEntity<Object> addItemToCart(@RequestBody AddItemRequest addItemRequest) {
        cartServices.addItem(addItemRequest.getUserId(), addItemRequest.getProductId(), addItemRequest.getQuantity());
        return ResponseEntity.ok("Added");
    }

    @GetMapping("/checkout")
    public ResponseEntity<Object> checkoutCart(@RequestParam(value = "userId", required = true)  Integer userId){
        Optional<Checkout> checkout = cartServices.checkout(userId);
        return checkout.<ResponseEntity<Object>>map(value ->
                new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>("No cart for the given customer", HttpStatus.UNPROCESSABLE_ENTITY));
    }
}
