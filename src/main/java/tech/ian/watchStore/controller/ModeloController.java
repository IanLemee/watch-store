package tech.ian.watchStore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.ian.watchStore.dto.ModeloDto;
import tech.ian.watchStore.entity.MarcaEntity;
import tech.ian.watchStore.entity.ModeloEntity;
import tech.ian.watchStore.service.ModeloService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/modelo")
@Tag(name = "Modelo", description = "Informacoes da Modelo")
public class ModeloController {

    @Autowired
    private ModeloService service;

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "criacao de um Modelo",
            description = "Essa funcao e responsavel por criar um Modelo"
    )
    @ApiResponses({
            @ApiResponse( responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = MarcaEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "Modelo ja existe")
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<ModeloEntity> create(@Valid @RequestBody ModeloDto dto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.execute(dto));
    }

    @GetMapping("/")
    @Operation(summary = "Buscar todos os Modelos",
            description = "Essa funcao e responsavel por buscar todos os Modelos"
    )
    @ApiResponses({
            @ApiResponse( responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = MarcaEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "O Modelo nao foi encontrado")
    })
    public ResponseEntity<List<ModeloEntity>> getAll() {

        return ResponseEntity.status(HttpStatus.FOUND).body(service.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar um Modelo pelo id",
            description = "Essa funcao e responsavel por buscar um Modelo especifica"
    )
    @ApiResponses({
            @ApiResponse( responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = MarcaEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "A marca nao foi encontrada")
    })
    public ResponseEntity<Optional<ModeloEntity>> getById(@PathVariable(value = "id") UUID id) {

        return ResponseEntity.status(HttpStatus.FOUND).body(service.getById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Deleção de um Modelo",
            description = "Essa funcao e responsavel por deletar um Modelo"
    )
    @ApiResponses({
            @ApiResponse( responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = MarcaEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "O Modelo nao foi encontrado")
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") UUID id) {

        return ResponseEntity.status(HttpStatus.OK).body(service.deleteById(id));
    }
}
