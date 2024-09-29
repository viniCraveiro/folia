package br.edu.unicesumar.folia.domain.banco;

import br.edu.unicesumar.folia.domain.Entidade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "BANCO")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Banco extends Entidade {

    @Column
    private String nome;
    @Column(length = 36)
    @NotNull
    private String agencia;
    @Column(length = 5)
    @NotNull
    private String agenciaDigito;
    @Column(length = 9)
    @NotNull
    private String conta;
    @Column(length = 9)
    @NotNull
    private String contaDigito;
    @Column(name = "DIGITO_VERIFICADOR", length = 4)
    @NotNull
    private String digitoVerificadorAgenciaConta;

}
