package br.edu.unicesumar.folia.controller.usuario;

import br.edu.unicesumar.folia.domain.boleto.Status;
import br.edu.unicesumar.folia.domain.usuario.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioFiltroDTO {
    private String empresaUUID;
    private String nome;
    private String identificacao;
    private TipoUsuario tipoUsuario;
}
