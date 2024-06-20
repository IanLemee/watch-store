package tech.ian.watchStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ian.watchStore.entity.MarcaEntity;

import java.util.Optional;
import java.util.UUID;

public interface MarcaRepository extends JpaRepository<MarcaEntity, UUID> {

    Optional<MarcaEntity> findByName(String name);
}
