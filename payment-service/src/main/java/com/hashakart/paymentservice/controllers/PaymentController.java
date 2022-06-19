package com.hashakart.paymentservice.controllers;


import com.hashakart.paymentservice.models.Payment;
import com.hashakart.paymentservice.models.Status;
import com.hashakart.paymentservice.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllCart() {
        return new ResponseEntity<>(paymentService.getAllPayments(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> payForCart(@RequestBody Integer userId){
        Status status = paymentService.completePay(userId);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

}
