package com.Business_Intelligence.bi_system_backend.dtos;

public record LoginResponse(String token, long expiresIn) {
}
