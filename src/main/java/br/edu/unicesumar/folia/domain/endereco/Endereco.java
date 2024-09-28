package br.edu.unicesumar.folia.domain.endereco;

import br.edu.unicesumar.folia.domain.Entidade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "ENDERECO")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco extends Entidade {

    @Column
    private String logradouro;
    @Column
    private String numeroResidencial;
    @Column
    private String complemento;
    @Column
    private String bairro;
    @Column
    private String cidade;
    @Column
    private String cep;
    @Column
    private String uf;

}
