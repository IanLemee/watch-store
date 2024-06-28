package tech.ian.watchStore.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ian.watchStore.dto.ModeloDto;
import tech.ian.watchStore.entity.ModeloEntity;
import tech.ian.watchStore.service.ModeloService;

@RestController
@RequestMapping("/modelo")
public class ModeloController {

    @Autowired
    private ModeloService service;

    @PostMapping("/")
    public ResponseEntity<ModeloEntity> create(@Valid @RequestBody ModeloDto dto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.execute(dto));
    }
}
