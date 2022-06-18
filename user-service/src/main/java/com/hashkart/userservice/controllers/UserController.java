package com.hashkart.userservice.controllers;

import com.hashkart.userservice.models.UserInfo;
import com.hashkart.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserInfo> save(@RequestBody UserInfo user) {
        return new ResponseEntity<>(userService.save(user),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllUser(){
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<UserInfo> getUser(
            @RequestParam("name") String name, @RequestParam("password") String password) {
        Optional<UserInfo> optUser = userService.getUser(name, password);
        if(optUser.isPresent()){
            return new ResponseEntity<>(optUser.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new UserInfo(), HttpStatus.OK);
    }
}
