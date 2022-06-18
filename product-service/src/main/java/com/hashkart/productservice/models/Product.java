package com.hashkart.productservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
public class Product {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private Double price;
    private Category category;
    private Integer quantity;
    private PaymentType paymentTypeAllowed;
    private Double userRating;
}
