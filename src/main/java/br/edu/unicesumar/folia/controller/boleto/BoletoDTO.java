package br.edu.unicesumar.folia.controller.boleto;

import br.edu.unicesumar.folia.domain.banco.Banco;
import br.edu.unicesumar.folia.domain.boleto.Boleto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class BoletoDTO {
    private String uuid;
    private BancoDTO banco;
    private String numero;
    private String status;
    private String estabelecimento;
    private LocalDate dataEmissao;
    private LocalDate dataVencimento;
    private BigDecimal valor;
    private String parcela;
    private String totalParcelas;
    private String descricao;
    private String url;

    @Getter
    @Setter
    public static class BancoDTO {
        private String uuid;
        private String nome;
        private String agencia;
        private String agenciaDigito;
        private String conta;
        private String contaDigito;

        public BancoDTO(Banco banco) {
            this.uuid = banco.getId().toString();
            this.nome = banco.getNome();
            this.agencia = banco.getAgencia();
            this.agenciaDigito = banco.getAgenciaDigito();
            this.conta = banco.getConta();
            this.contaDigito = banco.getContaDigito();
        }
    }

    public BoletoDTO(Boleto boleto) {
        this.uuid = boleto.getId().toString();
        this.numero = boleto.getTipoDocumento().toString();
        this.banco = new BancoDTO(boleto.getBanco());
        this.estabelecimento = boleto.getEstabelecimento();
        this.status = boleto.getStatus().toString();
        this.dataEmissao = boleto.getDataEmissao();
        this.dataVencimento = boleto.getDataVencimento();
        this.valor = boleto.getValor();
        this.parcela = boleto.getParcela();
        this.totalParcelas = boleto.getTotalParcelas();
        this.descricao = boleto.getDescricaoCobranca();
        this.url = boleto.getUrl();
    }

    public BoletoDTO() {
    }
}
