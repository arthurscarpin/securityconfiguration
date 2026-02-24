package com.github.arthurscarpin.security.controller.response;

import lombok.Builder;

@Builder
public record UserResponse(
        Long id,
        String name,
        String email
) {
}
