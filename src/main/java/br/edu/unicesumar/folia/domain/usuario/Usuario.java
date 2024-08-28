package br.edu.unicesumar.folia.domain.usuario;

import br.edu.unicesumar.folia.domain.Entidade;
import br.edu.unicesumar.folia.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "USUARIO")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario extends Entidade {

    @Column
    private String identificacao;
    @Column
    private String nome;
    @Column
    private String email;
    @ManyToOne
    @JoinColumn(name = "ENDERECO_FK")
    private Endereco endereco;

}
