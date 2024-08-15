package br.edu.unicesumar.folia.domain.usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "USUARIO")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    private UUID id = UUID.randomUUID();
    @Column
    @Setter
    private String identificacao;
    @Column
    @Setter
    private String nome;
    @Column
    @Setter
    private String email;
}
