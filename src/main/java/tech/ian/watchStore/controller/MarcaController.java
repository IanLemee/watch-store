package tech.ian.watchStore.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ian.watchStore.dto.MarcaDto;
import tech.ian.watchStore.entity.MarcaEntity;
import tech.ian.watchStore.service.MarcaService;

@RestController
@RequestMapping("/marca")
public class MarcaController {

    @Autowired
    private MarcaService service;

    @PostMapping("/")
    public ResponseEntity<MarcaEntity> create(@Valid @RequestBody MarcaDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.execute(dto));
    }
}
