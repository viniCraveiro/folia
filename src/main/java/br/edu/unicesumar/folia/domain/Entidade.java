package br.edu.unicesumar.folia.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Getter
public abstract class Entidade {

    @Column(name = "id")
    @Id
    private UUID id = UUID.randomUUID();


}
