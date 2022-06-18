package com.hashkart.cartservice.repositories;

import com.hashkart.cartservice.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
