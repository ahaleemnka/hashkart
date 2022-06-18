package com.hashkart.userservice.services;

import com.hashkart.userservice.models.UserInfo;
import com.hashkart.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public UserInfo save(UserInfo user) {
        return repository.save(user);
    }

    public Optional<UserInfo> getUser(String name, String password) {
        return repository.findByNameAndPassword(name, password);
    }

    public List<UserInfo> getAllUser() {
        return repository.findAll();
    }
}
