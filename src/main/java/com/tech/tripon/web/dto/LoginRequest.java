package com.tech.tripon.web.dto;

public record LoginRequest(
        String email,
        String password
) {
}
