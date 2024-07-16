package tech.ian.watchStore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.ian.watchStore.dto.MarcaDto;
import tech.ian.watchStore.entity.MarcaEntity;
import tech.ian.watchStore.service.MarcaService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/marca")
@Tag(name = "Marca", description = "Informacoes da Marca")
public class MarcaController {

    @Autowired
    private MarcaService service;

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "criacao de uma Marca",
    description = "Essa funcao e responsavel por criar uma Marca"
    )
    @ApiResponses({
            @ApiResponse( responseCode = "200", content = {
            @Content(schema = @Schema(implementation = MarcaEntity.class))
    }),
            @ApiResponse(responseCode = "400", description = "Marca ja existe")
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<MarcaEntity> create(@Valid @RequestBody MarcaDto dto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.execute(dto));
    }

    @GetMapping("/")
    @Operation(summary = "Buscar todas as Marcas",
            description = "Essa funcao e responsavel por buscar todas as Marcas"
    )
    @ApiResponses({
            @ApiResponse( responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = MarcaEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "A Marca nao foi encontrada")
    })
    public ResponseEntity<List<MarcaEntity>> getAll() {

        return ResponseEntity.status(HttpStatus.FOUND).body(service.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar uma Marca pelo id",
            description = "Essa funcao e responsavel por buscar uma Marca especifica"
    )
    @ApiResponses({
            @ApiResponse( responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = MarcaEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "A Marca nao foi encontrada")
    })
    public ResponseEntity<Optional<MarcaEntity>> getById(@PathVariable(value = "id") UUID id) {

        return ResponseEntity.status(HttpStatus.FOUND).body(service.getById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Deleção de uma Marca",
            description = "Essa funcao e responsavel por deletar uma Marca"
    )
    @ApiResponses({
            @ApiResponse( responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = MarcaEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "A Marca nao foi encontrada")
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") UUID id) {

        return ResponseEntity.status(HttpStatus.OK).body(service.deleteById(id));
    }
}
