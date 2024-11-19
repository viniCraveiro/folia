package br.edu.unicesumar.folia.controller.boleto;

import br.edu.unicesumar.folia.domain.boleto.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BoletoNapiDTO {
    private Long id;
    private String uuid;
    private String bancoNome;
    private String bancoAgencia;
    private String bancoConta;
    private String bancoContaDigito;
    private Double valor;
    private LocalDate dataVencimento;
    private LocalDate dataEmissao;
    private String carteira;
    private String pessoaIdentificacao;
    private String pessoaNome;
    private String estabelecimentoNome;
    private String estabelecimentoIdentificacao;
    private Long totalParcela;
    private String descricaoCobranca;
    private Long numeroDocumento;
    private Long parcela;
    private String parcelaDescricao;
    private String statusParcela;
    private String urlBoleto;
    private Double saldo;

    public BoletoNapiDTO(Long id, String uuid, String bancoNome, String bancoAgencia, String bancoConta, String bancoContaDigito, Double valor, LocalDate dataVencimento, LocalDate dataEmissao, String carteira, String pessoaIdentificacao, String pessoaNome, String estabelecimentoNome, String estabelecimentoIdentificacao, Long totalParcela, String descricaoCobranca, Long numeroDocumento, Long parcela, String parcelaDescricao, String statusParcela, String urlBoleto, Double saldo) {
        this.id = id;
        this.uuid = uuid;
        this.bancoNome = bancoNome;
        this.bancoAgencia = bancoAgencia;
        this.bancoConta = bancoConta;
        this.bancoContaDigito = bancoContaDigito;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.dataEmissao = dataEmissao;
        this.carteira = carteira;
        this.pessoaIdentificacao = pessoaIdentificacao;
        this.pessoaNome = pessoaNome;
        this.estabelecimentoNome = estabelecimentoNome;
        this.estabelecimentoIdentificacao = estabelecimentoIdentificacao;
        this.totalParcela = totalParcela;
        this.descricaoCobranca = descricaoCobranca;
        this.numeroDocumento = numeroDocumento;
        this.parcela = parcela;
        this.parcelaDescricao = parcelaDescricao;
        this.statusParcela = statusParcela;
        this.urlBoleto = urlBoleto;
        this.saldo = saldo;
    }

}
