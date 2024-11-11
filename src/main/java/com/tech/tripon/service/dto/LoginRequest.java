package com.tech.tripon.service.dto;

public record LoginRequest(
        String email,
        String password
) {
}
