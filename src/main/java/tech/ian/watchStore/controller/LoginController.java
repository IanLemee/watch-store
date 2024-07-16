package tech.ian.watchStore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ian.watchStore.dto.AuthenticationRequest;
import tech.ian.watchStore.dto.AuthenticationResponse;
import tech.ian.watchStore.entity.MarcaEntity;
import tech.ian.watchStore.entity.UserEntity;
import tech.ian.watchStore.service.TokenService;
import tech.ian.watchStore.service.UserService;

@RestController
@RequestMapping("/auth")
@Tag(name = "Login", description = "Login do usuario")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    @Operation(summary = "Login de um Usuario",
            description = "Essa funcao e responsavel por logar um Usuario"
    )
    @ApiResponses({
            @ApiResponse( responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = MarcaEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "Usuario nao existe")
    })
    public ResponseEntity login(@RequestBody AuthenticationRequest authenticationRequest) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationRequest.email(), authenticationRequest.password());

        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken( (UserEntity) auth.getPrincipal());

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }
}
