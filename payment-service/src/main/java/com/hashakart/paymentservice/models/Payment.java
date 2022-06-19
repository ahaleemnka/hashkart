package com.hashakart.paymentservice.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder(toBuilder = true)
public class Payment {

    @Id
    @GeneratedValue
    private Integer id;

    private Double amount;
    private Integer userId;
    private Integer orderId;
    private Status status;
}
