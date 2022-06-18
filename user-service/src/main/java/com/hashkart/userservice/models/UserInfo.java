package com.hashkart.userservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Getter
@Table(name = "user_info")
public class UserInfo {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Integer id;
    private String name;
    @Column(name = "pass")
    private String password;
    private String role;

}