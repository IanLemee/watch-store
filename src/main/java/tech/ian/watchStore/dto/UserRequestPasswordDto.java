package tech.ian.watchStore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import tech.ian.watchStore.entity.UserEntity;

public record UserRequestPasswordDto(@NotBlank
                                     @NotNull
                                        @Schema(example = "ian02313", requiredMode = Schema.RequiredMode.REQUIRED, description = "Redefina a sua senha")
                                     String password) {

    public UserEntity toModel() {
        UserEntity user = new UserEntity();
        user.setPassword(password);
        return user;
    }
}
