package br.edu.unicesumar.folia.domain.empresa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Table(name = "EMPRESA")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {

    @Id
    @Setter(AccessLevel.NONE)
    private UUID uuid;
    @Column
    private String nome;
    @Column
    private String cnpj;

}
