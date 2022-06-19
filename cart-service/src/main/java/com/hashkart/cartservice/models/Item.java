package com.hashkart.cartservice.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
public class Item {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer productId;
    private Integer quantity;
}
