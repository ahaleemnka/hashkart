package com.hashkart.cartservice.models;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer userId;
    @OneToMany(targetEntity=Item.class, fetch= FetchType.EAGER)
    private List<Item> itemList;

}
