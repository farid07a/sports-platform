package com.sports.backend.model;

import jakarta.validation.constraints.NotBlank;

public record Club(

                    Long id,

                    @NotBlank(message = "Name is required")
                    String name,
                    @NotBlank
                    String city,
                    @NotBlank
                    String sport
                   )
{
}
