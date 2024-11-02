package br.edu.unicesumar.folia.controller.boleto;

import br.edu.unicesumar.folia.controller.Conversor;
import br.edu.unicesumar.folia.domain.boleto.Boleto;
import br.edu.unicesumar.folia.domain.boleto.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class BoletoListaDTO {
    private Status status;
    private String nome;
    private String banco;
    private String parcela;
    private String vencimento;
    private String valor;

    public void alimentarDados(Boleto boleto) {
        if (boleto.getEmpresa() != null) {
            this.nome = boleto.getEmpresa().getNomeFantasia();
        }
        this.banco = boleto.getBanco().getNome();
        this.parcela = boleto.getTotalParcelas();
        this.vencimento = String.valueOf(boleto.getDataVencimento());
        this.valor = boleto.getValor();
        this.status = boleto.getStatus();
    }
}