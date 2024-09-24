package br.edu.unicesumar.folia.controller.usuario;

import br.edu.unicesumar.folia.domain.usuario.TipoUsuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponse {
    private String uuid;
    private String nome;
    private boolean valid;
    private TipoUsuario tipoUsuario;
}
