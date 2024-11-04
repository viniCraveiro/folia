package br.edu.unicesumar.folia.controller.boleto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BoletoFiltros {
    private String identificacao;
    private String nome;
    private Date dataEmissaoInicial;
    private Date dataEmissaoFinal;
    private Date dataVencimentoIncial;
    private Date dataVencimentoFinal;
    private String status;
}
