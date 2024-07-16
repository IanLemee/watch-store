package tech.ian.watchStore.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ian.watchStore.dto.MarcaDto;
import tech.ian.watchStore.entity.MarcaEntity;
import tech.ian.watchStore.exception.ProductFoundExeception;
import tech.ian.watchStore.exception.ProductNotFoundExeception;
import tech.ian.watchStore.repository.MarcaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository repository;

    public MarcaEntity execute(MarcaDto dto) {
        this.repository.findByName(dto.name()).ifPresent((marcaEntity) -> {
            throw new ProductFoundExeception("Produto ja existe");
        });

        var marcaEntity = new MarcaEntity();
        BeanUtils.copyProperties(dto, marcaEntity);
        return this.repository.save(marcaEntity);
    }

    public List<MarcaEntity> getAll() {

        return this.repository.findAll();
    }

    public Optional<MarcaEntity> getById(UUID id) {
        var marca = repository.findById(id);

        if (marca.isEmpty()) {
            throw new ProductNotFoundExeception();
        } else {
            return this.repository.findById(id);
        }
    }

    public String deleteById(UUID id) {

        var marca = repository.findById(id);

        if (marca.isPresent()) {
             repository.delete(marca.get());
        } else {
            throw new ProductNotFoundExeception();
        }

        return ("Marca deletada com sucesso!");
    }
}
