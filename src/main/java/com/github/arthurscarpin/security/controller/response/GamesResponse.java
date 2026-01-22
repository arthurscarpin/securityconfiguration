package com.github.arthurscarpin.security.controller.response;

import lombok.Builder;

@Builder
public record GamesResponse(
        Long id,
        String title,
        String description
) {
}
