package br.edu.unicesumar.folia.controller.usuarioboleto;

import br.edu.unicesumar.folia.domain.boleto.Status;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class UsuarioBoletoFiltradoDTO {
    private UUID uuid;
    private String identificacao;
    private String nome;
    private String usuario;
    private String banco;
    private BigDecimal valor;
    private String parcela;
    private LocalDate dataEmissao;
    private LocalDate dataVencimento;
    private Status status;
    private String url;

    // Construtor completo
    public UsuarioBoletoFiltradoDTO(UUID  uuid, String identificacao, String nome, String usuario, String banco, BigDecimal valor, String parcela, LocalDate dataEmissao, LocalDate dataVencimento, Status status, String url) {
        this.uuid = uuid;
        this.identificacao = identificacao;
        this.nome = nome;
        this.usuario = usuario;
        this.banco = banco;
        this.valor = valor;
        this.parcela = parcela;
        this.dataEmissao = dataEmissao;
        this.dataVencimento = dataVencimento;
        this.status = status;
        this.url = url;
    }

}
