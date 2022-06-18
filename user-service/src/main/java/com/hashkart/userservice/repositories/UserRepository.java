package com.hashkart.userservice.repositories;

import com.hashkart.userservice.models.UserInfo;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <UserInfo, String> {
    Optional<UserInfo> findByNameAndPassword(String name, String password);
}
