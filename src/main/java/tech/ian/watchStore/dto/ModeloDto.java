package tech.ian.watchStore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ModeloDto(@NotBlank(message = "O Campo deve ser preenchido")
                        @Schema(example = "daytona", requiredMode = Schema.RequiredMode.REQUIRED)
                        String name,
                        @NotBlank(message = "O Campo deve ser preenchido")
                                @Schema(example = "É um relogio de pulso cronografo", requiredMode = Schema.RequiredMode.REQUIRED)
                        String description,
                        @NotBlank(message = "O Campo deve ser preenchido")
                                @Schema(example = "2000", requiredMode = Schema.RequiredMode.REQUIRED)
                        String releaseDate,
                        @NotNull(message = "O Campo deve ser preenchido")
                                @Schema(example = "19000", requiredMode = Schema.RequiredMode.REQUIRED)
                        Double price,
                        @NotBlank(message = "O Campo deve ser preenchido")
                                @Schema(example = "Url da imagem", requiredMode = Schema.RequiredMode.REQUIRED)
                        String image,
                        @NotNull(message = "O Campo deve ser preenchido")
                        @Schema(example = "id da Marca", requiredMode = Schema.RequiredMode.REQUIRED)
                        UUID marcaId){
}
