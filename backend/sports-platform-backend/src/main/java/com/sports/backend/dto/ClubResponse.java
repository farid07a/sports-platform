package com.sports.backend.dto;

public record ClubResponse(
        Long id,
        String name,
        String city,
        String sport
) {
}
