package br.edu.unicesumar.folia.domain.usuario;

import br.edu.unicesumar.folia.domain.Entidade;
import br.edu.unicesumar.folia.domain.boleto.Boleto;
import br.edu.unicesumar.folia.domain.empresa.Empresa;
import br.edu.unicesumar.folia.domain.endereco.Endereco;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_USUARIO", length = 7)
    private TipoUsuario tipoUsuario;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ENDERECO_FK")
    private Endereco endereco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPRESA_FK", nullable = false)
    private Empresa empresa;


    public Usuario(Usuario usuario) {
        this.identificacao = usuario.getIdentificacao();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.usuario = usuario.getUsuario();
        this.senha = usuario.getSenha();
        this.tipoUsuario = usuario.getTipoUsuario();
        this.endereco = usuario.getEndereco();
        this.empresa = usuario.getEmpresa();
    }

    public Usuario(Usuario usuario, Empresa empresa) {
        this.identificacao = usuario.getIdentificacao();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.usuario = usuario.getUsuario();
        this.senha = usuario.getSenha();
        this.tipoUsuario = usuario.getTipoUsuario();
        this.endereco = usuario.getEndereco();
        this.empresa = empresa;
    }

}
