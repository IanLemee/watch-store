package tech.ian.watchStore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import tech.ian.watchStore.dto.*;
import tech.ian.watchStore.entity.MarcaEntity;
import tech.ian.watchStore.entity.UserEntity;
import tech.ian.watchStore.service.TokenService;
import tech.ian.watchStore.service.UserService;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/user")
@Tag(name = "Candidato", description = "Criacao do candidato")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/")
    @Operation(summary = "Criacao de um Usuario",
            description = "Essa funcao e responsavel por criar um Usuario"
    )
    @ApiResponses({
            @ApiResponse( responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = MarcaEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "Usuario ja existe")
    })
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserCreateRequest userRequestDto) throws MessagingException, UnsupportedEncodingException {
       UserEntity userEntity = userRequestDto.toModel();
       UserResponse userSaved = this.userService.registerUser(userEntity);
       return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
    }

    @GetMapping("/verify")
    @Operation(summary = "Verificacao de um Usuario",
            description = "Essa funcao e responsavel por verificar um Usuario"
    )
    @ApiResponses({
            @ApiResponse( responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = MarcaEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "Usuario ja autenticado")
    })
    public String verifyUser(@Param("code") String code) {
        if(userService.verify(code)) {
            return "verify_sucess";
        } else {
            return "verify_fail";
        }
    }

    @PutMapping("/{email}/change-password")
    @Operation(summary = "Trocar a senha de um Usuario",
            description = "Essa funcao e responsavel por trocar a senha de um Usuario"
    )
    @ApiResponses({
            @ApiResponse( responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = MarcaEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "Usuario nao encontrado")
    })
    public ResponseEntity<UserResponsePassword> changePassword(
            @PathVariable("email") String email,
            @RequestBody UserRequestPasswordDto userRequestPasswordDto) {
        UserResponsePassword userResponsePassword = userService.changePassword(email, userRequestPasswordDto);
        return ResponseEntity.status(HttpStatus.OK).body(userResponsePassword);
    }

    @GetMapping("/teste")
    public String teste (){
        return "voce esta logado";
    }
}
