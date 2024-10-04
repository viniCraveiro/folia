package br.edu.unicesumar.folia.controller.usuario;
import br.edu.unicesumar.folia.domain.usuario.RoleUsuario;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UsuarioTokenDTO {
    private String uuid;
    private String nome;
    private boolean valid;
    private RoleUsuario tipoUsuario;
}