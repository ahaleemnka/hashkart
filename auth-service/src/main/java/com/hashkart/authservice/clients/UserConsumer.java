package com.hashkart.authservice.clients;

import com.hashkart.authservice.entities.AuthRequest;
import com.hashkart.authservice.entities.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service")
public interface UserConsumer {

    @PostMapping("/users")
    UserInfo saveNewUser(@RequestBody AuthRequest authRequest);

    @GetMapping("/users")
    UserInfo getUser(@RequestParam("name")String name, @RequestParam("password") String password);
}
