package tech.ian.watchStore.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity(name = "tb_modelo")
@Data
public class ModeloEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    private String releaseDate;
    private Double price;
    private String image;

    @ManyToOne()
    @JoinColumn(name="marca_id", insertable = false, updatable = false)
    private MarcaEntity marcaEntity;

    @Column(name = "marca_id")
    private UUID marcaId;
}
