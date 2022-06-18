package com.hashkart.cartservice.controllers;

import com.hashkart.cartservice.services.CartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartServices cartServices;

    @GetMapping
    public ResponseEntity<Object> createCart(){
        return new ResponseEntity<>(cartServices.createNewCart(), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllCart(){
        return new ResponseEntity<>(cartServices.getAllCarts(), HttpStatus.OK);
    }

    @PostMapping("")
}
