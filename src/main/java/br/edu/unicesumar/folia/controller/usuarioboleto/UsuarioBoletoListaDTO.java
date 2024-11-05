package br.edu.unicesumar.folia.controller.usuarioboleto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioBoletoListaDTO {
    private String identificacao;
    private String nome;
    private String usuario;
    private Long quantidadeBoletos;
    private Long quantidadeBoletosAbertos;
    private Long quantidadeBoletosVencidos;
}
