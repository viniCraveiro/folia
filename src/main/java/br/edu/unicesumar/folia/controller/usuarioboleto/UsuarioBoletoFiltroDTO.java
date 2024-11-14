package br.edu.unicesumar.folia.controller.usuarioboleto;

import br.edu.unicesumar.folia.domain.banco.Banco;
import br.edu.unicesumar.folia.domain.boleto.Status;
import br.edu.unicesumar.folia.domain.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioBoletoFiltroDTO {
    private String userUuid;
    private String banco;
    private LocalDate dataInicialEmissao;
    private LocalDate dataFinalEmissao;
    private LocalDate dataInicialVencimento;
    private LocalDate dataFinalVencimento;
    private Status status;

}
