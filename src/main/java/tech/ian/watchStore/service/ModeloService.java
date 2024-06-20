package tech.ian.watchStore.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ian.watchStore.dto.ModeloDto;
import tech.ian.watchStore.entity.ModeloEntity;
import tech.ian.watchStore.exception.ProductFoundExeception;
import tech.ian.watchStore.repository.ModeloRepository;

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
}
