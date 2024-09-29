package br.edu.unicesumar.folia.domain.boleto;

import br.edu.unicesumar.folia.domain.Entidade;
import br.edu.unicesumar.folia.domain.banco.Banco;
import br.edu.unicesumar.folia.domain.empresa.Empresa;
import br.edu.unicesumar.folia.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "BOLETO")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Boleto extends Entidade {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BANCO_FK")
    private Banco banco;
    @Column
    private String valor;
    @Column
    private String dataVencimento;
    @Column
    private String totalParcelas;
    @Column
    private Integer convenio;
    @Column
    private Integer codigoCedente;
    @Column
    private Integer codigoTransmissao;
    @Column
    private Integer modalidade;
    @Column
    private Integer responsavelEmissao;
    @Column
    private Integer tipoCarteira;
    @Column
    private Integer tipoDocumento;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USUARIO_FK", nullable = false)
    private Usuario usuario;
    @Column
    private Integer caracteristicaTitulo;
    @Column
    private Integer codigoNegativacao;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "EMPRESA_FK")
    private Empresa dadosEmpresa;
    @Column
    private Integer identificacaoDistribuicao;
    @Column
    private String operacao;
    @Column
    private String chavePix;
    @Column
    private Integer tipoChavePix;
    @Enumerated(EnumType.STRING)
    @Column
    private Status status;
    @Column
    private String url;

}
