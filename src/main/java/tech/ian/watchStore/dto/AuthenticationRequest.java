package tech.ian.watchStore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthenticationRequest(
        @NotBlank
        @NotNull
        @Schema(example = "ian@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED)
        String email,
        @NotBlank
        @NotNull
        @Schema(example = "ian1234", requiredMode = Schema.RequiredMode.REQUIRED)
        String password) {
}
