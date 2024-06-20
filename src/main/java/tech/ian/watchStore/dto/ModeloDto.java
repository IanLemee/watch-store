package tech.ian.watchStore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ModeloDto(@NotBlank String name,
                        @NotBlank String description,
                        @NotBlank String releaseDate,
                        @NotNull Double price,
                        @NotBlank String image){
}
