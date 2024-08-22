package br.edu.unicesumar.folia.domain.empresa;

import br.edu.unicesumar.folia.domain.endereco.Endereco;
import jakarta.persistence.*;
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
    private String nomo_fantasia;

    @Column
    private String cnpj;

    @Column
    private String telefone;

    @Column
    private String razaoSocial;

    @Column
    private String email;

    @Column
    private String senha;

    @ManyToOne()
    @JoinColumn(name = "ENDERECO_FK")
    private Endereco endereco;
}
