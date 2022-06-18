package com.hashkart.cartservice.services;

import com.hashkart.cartservice.models.Cart;
import com.hashkart.cartservice.models.Item;
import com.hashkart.cartservice.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServices {

    @Autowired
    CartRepository cartRepository;
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public void addItem(Integer userId, Integer productId, Integer quantity) {
        Optional<Cart> optCart = cartRepository.findByUserId(userId);
        Cart cart = optCart.orElseGet(Cart::new);

        List<Item> itemList = cart.getItemList() == null ? new ArrayList<>() : cart.getItemList();
        itemList.add(Item.builder().productId(productId).quantity(quantity).build());

        cart.setItemList(itemList);
        cart.setUserId(userId);
        cartRepository.save(cart);
    }
}
