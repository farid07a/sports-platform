package com.sports.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClubRequest(

        /*
        * NotNull {name : null}
        * NotBlank("", " ",null)
        * NotEmpty("",null) but not " "
        *
         */

        @Size(max = 100,message = "Name must not exceed 100 characters")
        @NotBlank(message = "Name is required")
        String name,
        @Size(max = 100,message = "Name must not exceed 100 characters")
        @NotBlank(message = "City is required")
        String city,
        @Size(max = 100)
        @NotBlank(message = "Sport is required")
        String sport
) {
}
