package com.hashkart.cartservice.services;

import com.hashkart.cartservice.clients.ProductService;
import com.hashkart.cartservice.entities.Product;
import com.hashkart.cartservice.models.Cart;
import com.hashkart.cartservice.models.Checkout;
import com.hashkart.cartservice.models.Item;
import com.hashkart.cartservice.repositories.CartRepository;
import com.hashkart.cartservice.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServices {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ProductService productService;

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public void addItem(Integer userId, Integer productId, Integer quantity) {
        Optional<Cart> optCart = cartRepository.findByUserId(userId);
        Cart cart = optCart.orElseGet(Cart::new);

        List<Item> itemList = cart.getItemList() == null ? new ArrayList<>() : cart.getItemList();
        itemList.add(Item.builder().productId(productId).quantity(quantity).build());

        cart.setItemList(itemRepository.saveAll(itemList));
        cart.setUserId(userId);
        cartRepository.save(cart);
    }

    public Optional<Checkout> checkout(Integer userId) {
        Optional<Cart> optCart = cartRepository.findByUserId(userId);
        if(!optCart.isPresent()){
            return Optional.empty();
        }
        double totalPrice = 0.0;
        for (Item item : optCart.get().getItemList()) {
            Product product = productService.getProductById(item.getProductId());
            totalPrice += product.getPrice() * item.getQuantity();
        }
        return Optional.of(
                Checkout.builder().cart(optCart.get()).totalPrice(totalPrice).build());
    }
}
