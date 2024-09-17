package com.example.etulas.user.dto;

import com.example.etulas.user.User;

public record UserResponse(
        Long id,
        String name
) {
    public static UserResponse fromModel(User user) {
        return new UserResponse(
                user.getId(),
                user.getName()
        );
    }

}