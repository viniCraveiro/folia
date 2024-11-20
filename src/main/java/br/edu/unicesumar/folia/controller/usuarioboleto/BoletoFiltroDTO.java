package br.edu.unicesumar.folia.controller.usuarioboleto;

import br.edu.unicesumar.folia.domain.boleto.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoletoFiltroDTO {
    private String usuarioUUID;
    private String empresaUUID;
    private String banco;
    private String nome;
    private String identificacao;
    private String numero;
    private LocalDate dataInicialEmissao;
    private LocalDate dataFinalEmissao;
    private LocalDate dataInicialVencimento;
    private LocalDate dataFinalVencimento;
    private Status status;
}
