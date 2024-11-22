package br.edu.unicesumar.folia.controller.usuario;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private UUID id;
    private String identificacao;
    private String nome;
    private Long boletosTotal;
    private Long boletosPagos;
}
