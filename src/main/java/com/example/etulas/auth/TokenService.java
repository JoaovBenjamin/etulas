package com.example.etulas.auth;


import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.etulas.user.User;
import com.example.etulas.user.UserRepository;

@Service
public class TokenService {
     
    private Algorithm algorithm;

    @Autowired
    UserRepository repository;


  public TokenService(UserRepository userRepository,@Value("${jwt.secret}") String secret) {
        algorithm = Algorithm.HMAC256(secret);
        userRepository = this.repository;
    }

    public Token createToken(String name){
        var expiresAt = LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.ofHours(-3));
        String token = JWT.create()
                .withSubject(name)
                .withIssuer("etulas")
                .withExpiresAt(expiresAt)
                .sign(algorithm);

        return new Token(token, "JWT");
    }

    public User getUserFromToken(String token) {
        var name = JWT.require(algorithm)
                .build()
                .verify(token)
                .getSubject();

        return repository.findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}