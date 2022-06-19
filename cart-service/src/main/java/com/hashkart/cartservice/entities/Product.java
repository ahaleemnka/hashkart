package com.hashkart.cartservice.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    private Integer id;
    private String name;
    private Double price;
    private Integer quantity;
    private Double userRating;
}
