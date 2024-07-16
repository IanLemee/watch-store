package tech.ian.watchStore.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ian.watchStore.dto.ModeloDto;
import tech.ian.watchStore.entity.ModeloEntity;
import tech.ian.watchStore.exception.ProductFoundExeception;
import tech.ian.watchStore.exception.ProductNotFoundExeception;
import tech.ian.watchStore.repository.ModeloRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ModeloService {

    @Autowired
    private ModeloRepository repository;

    public ModeloEntity execute(ModeloDto dto) {
        this.repository.findByNameAndReleaseDate(dto.name(), dto.releaseDate()).ifPresent((marcaEntity) -> {
            throw new ProductFoundExeception("O produto ja existe");
        });

        var modeloEntity = new ModeloEntity();
        BeanUtils.copyProperties(dto, modeloEntity);
        return this.repository.save(modeloEntity);
    }

    public List<ModeloEntity> getAll() {

        return this.repository.findAll();
    }

    public Optional<ModeloEntity> getById(UUID id) {
        var modelo = repository.findById(id);

        if (modelo.isEmpty()) {
            throw new ProductNotFoundExeception();
        } else {
            return this.repository.findById(id);
        }
    }

    public String deleteById(UUID id) {

        var modelo = repository.findById(id);

        if (modelo.isPresent()) {
            repository.delete(modelo.get());
        } else {
            throw new ProductNotFoundExeception();
        }

        return ("Marca deletada com sucesso!");
    }
}
