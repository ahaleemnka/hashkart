package com.hashakart.paymentservice.services;

import com.hashakart.paymentservice.clients.CartService;
import com.hashakart.paymentservice.entities.Checkout;
import com.hashakart.paymentservice.models.Payment;
import com.hashakart.paymentservice.models.Status;
import com.hashakart.paymentservice.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    CartService cartService;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Status completePay(Integer userId) {
        try {
            Checkout checkout = cartService.getCheckout(userId);
            paymentRepository.save(Payment.builder().status(Status.completed).userId(userId).amount(checkout.getTotalPrice()).build());
            return Status.completed;
        }
        catch (RuntimeException ex){
            ex.printStackTrace();
            return Status.failed;
        }
    }
}
