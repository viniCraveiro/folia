package br.edu.unicesumar.folia.domain.endereco;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Table(name = "ENDERECO")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    @Id
    @Setter(AccessLevel.NONE)
    private UUID uuid;
    @Column
    private String logradouro;
    @Column
    private String numeroResidencial;
    @Column
    private String Complemento;
    @Column
    private String bairro;
    @Column
    private String cidade;
    @Column
    private String cep;
    @Column
    private String uf;

}
