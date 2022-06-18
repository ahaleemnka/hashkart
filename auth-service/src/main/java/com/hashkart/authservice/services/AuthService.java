package com.hashkart.authservice.services;

import com.hashkart.authservice.clients.UserConsumer;
import com.hashkart.authservice.entities.AuthRequest;
import com.hashkart.authservice.entities.AuthResponse;
import com.hashkart.authservice.entities.UserInfo;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import javax.security.auth.login.CredentialException;

@Service
public class AuthService {
    @Autowired
    private JwtUtil jwt;

    @Autowired
    UserConsumer userConsumer;

    public AuthResponse register(AuthRequest authRequest) {

        UserInfo userInfo = userConsumer.saveNewUser(authRequest);
        Assert.notNull(userInfo, "Failed to register user. Please try again later");
        return generateToken(userInfo);
    }

    public AuthResponse login(String name, String password) throws CredentialException {

        UserInfo userInfo = userConsumer.getUser(name,password);
        Assert.notNull(userInfo, "Failed to register user. Please try again later");

        if(userInfo.getId() == null){
            throw new CredentialException("Invalid username or password");
        }

        return generateToken(userInfo);
    }

    private AuthResponse generateToken(UserInfo userInfo){
        String accessToken = jwt.generate(userInfo, "ACCESS");
        String refreshToken = jwt.generate(userInfo, "REFRESH");

        return new AuthResponse(accessToken, refreshToken);
    }
}
