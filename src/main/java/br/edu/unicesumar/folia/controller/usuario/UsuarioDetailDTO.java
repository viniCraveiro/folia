package br.edu.unicesumar.folia.controller.usuario;


import br.edu.unicesumar.folia.domain.usuario.TipoUsuario;
import br.edu.unicesumar.folia.domain.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDetailDTO {
    private UUID id;
    private String identificacao;
    private String nome;
    private String email;
    private String username;
    private String senha;
    private String confirmarSenha;
    private TipoUsuario tipoUsuario;

    public UsuarioDetailDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.identificacao = usuario.getIdentificacao();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.username = usuario.getUsername();
        this.senha = usuario.getSenha();
        this.confirmarSenha = usuario.getSenha();
        this.tipoUsuario = usuario.getTipoUsuario();
    }
}
