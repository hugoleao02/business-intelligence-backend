package com.Business_Intelligence.bi_system_backend.dtos;

import com.Business_Intelligence.bi_system_backend.entities.User;
import com.Business_Intelligence.bi_system_backend.enums.Role;

import java.util.List;
import java.util.UUID;

public record UserResponseDto(UUID id, String name, String email, List<Role> roles) {
    public static UserResponseDto fromUser(User user) {
        return new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getRoles());
    }
}
