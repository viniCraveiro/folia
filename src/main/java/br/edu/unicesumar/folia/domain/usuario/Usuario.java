package br.edu.unicesumar.folia.domain.usuario;

import br.edu.unicesumar.folia.domain.Entidade;
import br.edu.unicesumar.folia.domain.endereco.Endereco;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Table(name = "USUARIO")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario extends Entidade {

    @NotBlank(message = "Identificação não pode ser vazia")
    @Column
    private String identificacao;
    @Column
    private String nome;
    @Column
    private String email;
    @Column
    private String usuario;
    @Column
    private String senha;
    @ElementCollection(targetClass = RoleUsuario.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "USUARIO_ROLE", joinColumns = @JoinColumn(name = "USUARIO_ID"))
    @Column(name = "ROLE_USUARIO", length = 7)
    private Set<RoleUsuario> roles = new HashSet<>();
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ENDERECO_FK")
    private Endereco endereco;

    public Usuario(Usuario usuario) {
        this.identificacao = usuario.getIdentificacao();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.usuario = usuario.getUsuario();
        this.senha = usuario.getSenha();
        this.roles = usuario.getRoles();
        this.endereco = usuario.getEndereco();
    }

}
