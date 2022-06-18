package com.hashkart.productservice.services;

import com.hashkart.productservice.exception.CategoryNotFound;
import com.hashkart.productservice.models.Category;
import com.hashkart.productservice.models.PaymentType;
import com.hashkart.productservice.models.Product;
import com.hashkart.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @PostConstruct
    public void init(){
        productRepository.save(Product.builder()
                .name("Rice")
                .price(50.0)
                .category(Category.GROCERY)
                .quantity(5)
                .paymentTypeAllowed(PaymentType.DEBIT_CARD)
                .userRating(3.8)
                .build());
        productRepository.save(Product.builder()
                .name("Wheat")
                .price(45.0)
                .category(Category.GROCERY)
                .quantity(10)
                .paymentTypeAllowed(PaymentType.CREDIT_CARD)
                .userRating(2.8)
                .build());
        productRepository.save(Product.builder()
                .name("Iphone")
                .price(53599.0)
                .category(Category.GADGETS)
                .quantity(2)
                .paymentTypeAllowed(PaymentType.DEBIT_CARD)
                .userRating(4.1)
                .build());
        productRepository.save(Product.builder()
                .name("Shoes")
                .price(1500.0)
                .category(Category.FOOTWEAR)
                .quantity(6)
                .paymentTypeAllowed(PaymentType.CASH_ON_DELIVERY)
                .userRating(4.3)
                .build());
        productRepository.save(Product.builder()
                .name("Sandals")
                .price(1200.0)
                .category(Category.FOOTWEAR)
                .quantity(8)
                .paymentTypeAllowed(PaymentType.CREDIT_CARD)
                .userRating(4.0)
                .build());
        productRepository.save(Product.builder()
                .name("Shirt")
                .price(1400.0)
                .category(Category.CLOTHING)
                .quantity(8)
                .paymentTypeAllowed(PaymentType.CASH_ON_DELIVERY)
                .userRating(3.9)
                .build());
        productRepository.save(Product.builder()
                .name("Pants")
                .price(1600.0)
                .category(Category.CLOTHING)
                .quantity(8)
                .paymentTypeAllowed(PaymentType.CASH_ON_DELIVERY)
                .userRating(4.2)
                .build());
        productRepository.save(Product.builder()
                .name("Jackets")
                .price(2100.0)
                .category(Category.CLOTHING)
                .quantity(4)
                .paymentTypeAllowed(PaymentType.CASH_ON_DELIVERY)
                .userRating(3.3)
                .build());
    }
    public List<Product> getAll() {
        return productRepository.findAll();
    }


    public Optional<Product> getProductById(Integer productId) {
        return productRepository.findById(productId);
    }
    public List<Product> getProductByCategory(String categoryValue) {
        Optional<Category> optCategory = Category.get(categoryValue);
        if(!optCategory.isPresent()){
            throw new CategoryNotFound(categoryValue);
        }
        return productRepository.findAllByCategory(optCategory.get());
    }

    public List<Product> getProductByProductName(String productName) {
        return productRepository.findAllByNameContainingIgnoreCase(productName);
    }

    public List<Product> getSortedProductByRating(String sortOrder) {
        if(sortOrder.equalsIgnoreCase("ASC")){
            return productRepository.findAll().stream().sorted((o1, o2) -> {
                if(o1.getUserRating() > o2.getUserRating()){
                    return 1;
                } else if(o1.getUserRating() < o2.getUserRating()){
                    return -1;
                }
                return 0;
            }).collect(Collectors.toList());
        } else {
            return productRepository.findAll().stream().sorted((o1, o2) -> {
                if(o1.getUserRating() > o2.getUserRating()){
                    return -1;
                } else if(o1.getUserRating() < o2.getUserRating()){
                    return 1;
                }
                return 0;
            }).collect(Collectors.toList());
        }
    }

    public List<Product> getSortedProductByPrice(String sortOrder) {
        if(sortOrder.equalsIgnoreCase("ASC")){
            return productRepository.findAll().stream().sorted((o1, o2) -> {
                if(o1.getPrice() > o2.getPrice()){
                    return 1;
                } else if(o1.getPrice() < o2.getPrice()){
                    return -1;
                }
                return 0;
            }).collect(Collectors.toList());
        } else {
            return productRepository.findAll().stream().sorted((o1, o2) -> {
                if(o1.getPrice() > o2.getPrice()){
                    return -1;
                } else if(o1.getPrice() < o2.getPrice()){
                    return 1;
                }
                return 0;
            }).collect(Collectors.toList());
        }
    }

    public List<Product> getFilteredProductByRating(final Double start, final Double end) {
        return productRepository.findAll().stream().filter(i -> i.getUserRating() > start && i.getUserRating() <= end).collect(Collectors.toList());
    }

    public List<Product> getFilteredProductByPrice(final Double start, final Double end) {
        return productRepository.findAll().stream().filter(i -> i.getPrice() > start && i.getPrice() <= end).collect(Collectors.toList());
    }
}
