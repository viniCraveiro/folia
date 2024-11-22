package br.edu.unicesumar.folia.controller.boleto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoletoInformacoesDTO {
    private Long quantidadeBoletos;
    private Long quantidadeBoletosAberto;
    private Long quantidadeBoletosPago;
    private Long quantidadeBoletosVencido;
    private Long quantidadeBoletosProximosVencimento;
}
