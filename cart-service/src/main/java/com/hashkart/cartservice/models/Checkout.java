package com.hashkart.cartservice.models;

import lombok.*;

@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Checkout{
    private Cart cart;
    private Double totalPrice;
}
