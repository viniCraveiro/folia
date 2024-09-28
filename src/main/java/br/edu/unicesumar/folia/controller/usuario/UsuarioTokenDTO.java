package br.edu.unicesumar.folia.controller.usuario;

import br.edu.unicesumar.folia.domain.Entidade;
import br.edu.unicesumar.folia.domain.usuario.TipoUsuario;

public class UsuarioTokenDTO extends Entidade {
    public String nome;
    public String valid;
    public TipoUsuario tipoUsuario;

    UsuarioTokenDTO(){

    }

}
