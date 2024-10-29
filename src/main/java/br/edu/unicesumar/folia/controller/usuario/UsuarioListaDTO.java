package br.edu.unicesumar.folia.controller.usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioListaDTO {
    private String identificacao;
    private String nome;
    private String usuario;
    private Long quantidadeBoletos;
    private Long quantidadeBoletosVencidos;
}
