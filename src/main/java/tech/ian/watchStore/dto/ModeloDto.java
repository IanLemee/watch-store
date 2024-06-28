package tech.ian.watchStore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ModeloDto(@NotBlank(message = "O Campo deve ser preenchido") String name,
                        @NotBlank(message = "O Campo deve ser preenchido") String description,
                        @NotBlank(message = "O Campo deve ser preenchido") String releaseDate,
                        @NotNull(message = "O Campo deve ser preenchido") Double price,
                        @NotBlank(message = "O Campo deve ser preenchido") String image,
                        @NotNull(message = "O Campo deve ser preenchido") UUID marcaId){
}
