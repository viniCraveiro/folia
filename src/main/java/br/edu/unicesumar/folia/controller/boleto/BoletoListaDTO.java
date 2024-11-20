package br.edu.unicesumar.folia.controller.boleto;

import br.edu.unicesumar.folia.controller.Conversor;
import br.edu.unicesumar.folia.domain.boleto.Boleto;
import br.edu.unicesumar.folia.domain.boleto.Status;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Getter
@Setter
public class BoletoListaDTO {
    private String uuid;
    private Status status;
    private String nome;
    private String banco;
    private String parcela;
    private LocalDate vencimento;
    private BigDecimal valor;

    public void alimentarDados(Boleto boleto) {
        this.uuid = boleto.getId().toString();
        if (boleto.getEmpresa() != null) {
            this.nome = boleto.getEmpresa().getNomeFantasia();
        }
        this.banco = boleto.getBanco().getNome();
        this.parcela = boleto.getTotalParcelas();
        this.vencimento = boleto.getDataVencimento();
        this.valor = boleto.getValor();
        this.status = boleto.getStatus();
    }
}