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
        if (boleto.getDadosEmpresa() != null) {
            this.nome = boleto.getDadosEmpresa().getNomeFantasia();
        }
        this.banco = Conversor.obterNomeBanco(Integer.parseInt(boleto.getBanco().getNome()));
        this.parcela = boleto.getTotalParcelas();
        this.vencimento = boleto.getDataVencimento() != null ? boleto.getDataVencimento().toString() : "N/A";
        this.valor = boleto.getValor() != null ? boleto.getValor().toString() : "0.00";
    }
}
