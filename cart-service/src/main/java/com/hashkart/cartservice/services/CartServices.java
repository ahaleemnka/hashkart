package com.hashkart.cartservice.services;

import com.hashkart.cartservice.models.Cart;
import com.hashkart.cartservice.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServices {

    @Autowired
    CartRepository cartRepository;
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }
}
