package com.example.etulas.user.dto;




import com.example.etulas.user.User;

public record UserFormRequest(
        String name,
        String role,
        String password
) {
    public User toModel() {
        return User.builder()
                .name(name)
                .password(password)
                .role(role)
                .build();
    }
}