package br.edu.unicesumar.folia.controller.usuario;

import br.edu.unicesumar.folia.domain.usuario.RoleUsuario;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class JwtTokenDTO {
    private String id;
    private String idendificacao;
    private String nome;
    private boolean valid;
    private Set<RoleUsuario> tipoUsuario;
}