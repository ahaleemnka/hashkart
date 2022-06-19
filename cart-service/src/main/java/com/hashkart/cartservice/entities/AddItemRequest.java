package com.hashkart.cartservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AddItemRequest {
    private Integer userId;
    private Integer productId;
    private Integer quantity;
}
