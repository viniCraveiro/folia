package br.edu.unicesumar.folia.controller.usuario;

import br.edu.unicesumar.folia.domain.Entidade;
import lombok.Getter;

@Getter
public class UsuarioLoginDTO extends Entidade {
    public String identificacao;
    public String senha;

    UsuarioLoginDTO(){

    }

}
