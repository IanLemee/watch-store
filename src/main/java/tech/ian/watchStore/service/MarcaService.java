package tech.ian.watchStore.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ian.watchStore.dto.MarcaDto;
import tech.ian.watchStore.entity.MarcaEntity;
import tech.ian.watchStore.exception.ProductFoundExeception;
import tech.ian.watchStore.repository.MarcaRepository;

import java.util.Optional;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository repository;

    public MarcaEntity execute(MarcaDto dto) {
        this.repository.findByName(dto.name()).ifPresent((marcaEntity) -> {
            throw new ProductFoundExeception("Produto ja existe");
        });
        MarcaEntity marcaEntity = new MarcaEntity();
        BeanUtils.copyProperties(dto, marcaEntity);
        return this.repository.save(marcaEntity);
    }
}
