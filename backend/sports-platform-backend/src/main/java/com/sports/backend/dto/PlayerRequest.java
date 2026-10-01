package com.sports.backend.dto;

import jakarta.validation.constraints.NotBlank;

public record PlayerRequest(
        @NotBlank
        String name,
        @NotBlank
        String position,

        Long clubId
) {
}
