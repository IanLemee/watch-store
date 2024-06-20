package tech.ian.watchStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ian.watchStore.entity.ModeloEntity;

import java.util.Optional;
import java.util.UUID;

public interface ModeloRepository extends JpaRepository<ModeloEntity, UUID> {

    Optional<ModeloEntity> findByNameAndReleaseDate(String name, String releaseDate);
}
