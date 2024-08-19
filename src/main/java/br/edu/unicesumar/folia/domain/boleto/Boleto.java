package br.edu.unicesumar.folia.domain.boleto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Table(name = "BOLETO")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Boleto {

    @Id
    @Setter(AccessLevel.NONE)
    private UUID uuid;
    @Column
    private int agencia;
    @Column
    private int agenciaDigito;
    @Column
    private int conta;
    @Column
    private int contaDigito;
    @Column
    private int digitoVerificadorAgenciaConta;
    @Column
    private int convenio;
    @Column
    private int codigoCedente;
    @Column
    private int codigoTransmissao;
    @Column
    private int modalidade;
    @Column
    private int responsavelEmissao;
    @Column
    private int tipoCarteira;
    @Column
    private int tipoDocumento;
    @Column
    private int tipoInscricao;
    @Column
    private int caracteristicaTitulo;
    @Column
    private int codigoNegativacao;
    @Column
    private String cnpj;
    @Column
    private String nome;
    @Column
    private String logradouro;
    @Column
    private String numeroResidencial;
    @Column
    private String complemento;
    @Column
    private String bairro;
    @Column
    private String cidade;
    @Column
    private String cep;
    @Column
    private String uf;
    @Column
    private String telefone;
    @Column
    private int identificacaoDistribuicao;
    @Column
    private String operacao;
    @Column
    private String chavePix;
    @Column
    private int tipoChavePix;

}
