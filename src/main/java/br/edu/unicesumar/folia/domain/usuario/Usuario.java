package br.edu.unicesumar.folia.domain.usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Table(name = "USUARIO")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @Setter(AccessLevel.NONE)
    private UUID id = UUID.randomUUID();
    @Column
    private String identificacao;
    @Column
    private String nome;
    @Column
    private String email;

}
