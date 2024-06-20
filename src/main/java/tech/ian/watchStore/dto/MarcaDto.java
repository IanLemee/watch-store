package tech.ian.watchStore.dto;

import jakarta.validation.constraints.NotBlank;

public record MarcaDto(@NotBlank(message = "O Campo deve ser preenchido") String name,
                       @NotBlank(message = "O Campo deve ser preenchido") String companyHistory,
                       @NotBlank(message = "O Campo deve ser preenchido") String companyFoundingYear) {
}
