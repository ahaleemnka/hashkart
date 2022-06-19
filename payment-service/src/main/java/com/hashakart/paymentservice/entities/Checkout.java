package com.hashakart.paymentservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Checkout {
    private Double totalPrice;
    private Integer userId;
}
