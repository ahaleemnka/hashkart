package com.hashkart.productservice.controllers;

import com.hashkart.productservice.exception.CategoryNotFound;
import com.hashkart.productservice.models.Product;
import com.hashkart.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllProductDetails() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable("id") Integer productId) {
        Optional<Product> optProduct = productService.getProductById(productId);
        return optProduct.<ResponseEntity<Object>>map(product -> new ResponseEntity<>(product, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>("Product not found", HttpStatus.UNPROCESSABLE_ENTITY));

    }

    @GetMapping("/name/{productName}/all")
    public ResponseEntity<Object> getProductByName(@PathVariable("productName") String productName) {
        return new ResponseEntity<>(productService.getProductByProductName(productName), HttpStatus.OK);
    }

    @GetMapping("/category/{category}/all")
    public ResponseEntity<Object> getProductByCategory(@PathVariable("category") String category) {
        try {
            return new ResponseEntity<>(productService.getProductByCategory(category), HttpStatus.OK);
        } catch (CategoryNotFound ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/{sortAttribute}/{sortOrder}")
    public ResponseEntity<Object> getSortedProducts(@PathVariable("sortAttribute") String sortAttribute, @PathVariable("sortOrder") String sortOrder) {
        try {
            if(sortAttribute.equalsIgnoreCase("rating")) {
                return new ResponseEntity<>(productService.getSortedProductByRating(sortOrder), HttpStatus.OK);
            } else if(sortAttribute.equalsIgnoreCase("price")) {
                return new ResponseEntity<>(productService.getSortedProductByPrice(sortOrder), HttpStatus.OK);
            }
            return new ResponseEntity<>("Sorting on : "+sortAttribute + " is NOT allowed", HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (CategoryNotFound ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/filter/{filterAttribute}")
    public ResponseEntity<Object> getFilteredProducts(@PathVariable("filterAttribute") String filterAttribute,@QueryParam("start") Double start, @QueryParam("end") Double end) {
        try {
            if(filterAttribute.equalsIgnoreCase("rating")) {
                return new ResponseEntity<>(productService.getFilteredProductByRating(start, end), HttpStatus.OK);
            } else if(filterAttribute.equalsIgnoreCase("price")) {
                return new ResponseEntity<>(productService.getFilteredProductByPrice(start, end), HttpStatus.OK);
            }
            return new ResponseEntity<>("Filtering on : "+filterAttribute + " is NOT allowed", HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (CategoryNotFound ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
