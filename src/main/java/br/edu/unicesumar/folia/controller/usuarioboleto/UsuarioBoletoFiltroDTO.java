package br.edu.unicesumar.folia.controller.usuarioboleto;

import br.edu.unicesumar.folia.domain.boleto.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class UsuarioBoletoFiltroDTO {
    private UUID usuarioId;
    private String identificacao;
    private String nome;
    private LocalDate dataInicialEmissao;
    private LocalDate dataFinalEmissao;
    private LocalDate dataInicialVencimento;
    private LocalDate dataFinalVencimento;
    private Status status;

}
