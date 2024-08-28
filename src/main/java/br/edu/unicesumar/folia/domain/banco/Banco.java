package br.edu.unicesumar.folia.domain.banco;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Table(name = "BANCO")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Banco {

    @Id
    @Setter(AccessLevel.NONE)
    private UUID uuid;
    @Column
    private int agencia;
    @Column
    private int agenciaDigito;
    @Column
    private int conta;
    @Column
    private int contaDigito;
    @Column
    private int digitoVerificadorAgenciaConta;

}
