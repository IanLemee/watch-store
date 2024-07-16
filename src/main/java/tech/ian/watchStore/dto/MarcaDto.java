package tech.ian.watchStore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record MarcaDto(@NotBlank(message = "O Campo deve ser preenchido")
                        @Schema(example = "Rolex", requiredMode = Schema.RequiredMode.REQUIRED)
                       String name,
                       @NotBlank(message = "O Campo deve ser preenchido")
                               @Schema(example = "A marca foi fundanda atraves da parceria de dois...", requiredMode = Schema.RequiredMode.REQUIRED)
                       String companyHistory,
                       @NotBlank(message = "O Campo deve ser preenchido")
                               @Schema(example = "2000", requiredMode = Schema.RequiredMode.REQUIRED)
                       String companyFoundingYear) {
}
