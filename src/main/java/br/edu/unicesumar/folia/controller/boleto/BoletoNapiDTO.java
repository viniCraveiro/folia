package br.edu.unicesumar.folia.controller.boleto;

import br.edu.unicesumar.folia.domain.boleto.Status;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoletoNapiDTO {

    private String bancoNome;
    private String bancoAgencia;
    private String bancoConta;
    private String bancoContaDigito;
    private BigDecimal valor;
    private LocalDate dataVencimento;
    private LocalDate dataEmissao;
    private String pessoaIdentificacao;
    private String pessoaNome;
    private String estabelecimentoNome;
    private String estabelecimentoIdentificacao;
    private String totalParcela;
    private Integer numeroDocumento;
    private Long parcela;
    private String statusParcela;
    private String urlBoleto;
    private String parcelaDescricao;


}
