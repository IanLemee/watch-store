package tech.ian.watchStore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import tech.ian.watchStore.entity.UserEntity;

public record UserCreateRequest(@NotNull(message = "O nome não pode ser nulo")
                              @NotBlank(message = "O nome não pode ser vazio")
                                @Schema(example = "Ian", requiredMode = Schema.RequiredMode.REQUIRED)
                              String name,
                                @NotNull(message = "Email não pode ser nulo")
                              @NotBlank(message = "Email não pode ser vazio")
                                @Schema(example = "ian@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED)
                              @Email
                              String email,
                                @NotNull(message = "Senha não pode ser nula")
                              @NotBlank(message = "Senha não pode ser vazia")
                              @Size(min = 8, message = "A senha deve conter no minimo 8 caracteres")
                                        @Schema(example = "ian123", requiredMode = Schema.RequiredMode.REQUIRED)
                              String password,

                                @NotNull()
                                @NotBlank()
                                        @Schema(example = "admin", requiredMode = Schema.RequiredMode.REQUIRED)
                                String role) {

    public UserEntity toModel(){
        return new UserEntity(name,email,password,role);
    }
}
