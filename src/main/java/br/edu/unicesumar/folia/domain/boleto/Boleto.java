package br.edu.unicesumar.folia.domain.boleto;

import br.edu.unicesumar.folia.domain.Entidade;
import br.edu.unicesumar.folia.domain.banco.Banco;
import br.edu.unicesumar.folia.domain.empresa.Empresa;
import br.edu.unicesumar.folia.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "BOLETO")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Boleto extends Entidade {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BANCO_FK")
    private Banco banco; //traz
    @Column
    private BigDecimal valor; //traz
    @Column
    private LocalDate dataEmissao; //traz
    @Column
    private LocalDate dataVencimento; //traz
    @Column
    private String totalParcelas; //traz
    @Column
    private String parcela; //traz
    @Column
    private String descricaoCobranca; //traz
    // @Column
    private String estabelecimento; //traz
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
    private Empresa empresa;
    @Column
    private Integer identificacaoDistribuicao;
    @Column
    private String operacao; //traz
    @Column
     private String chavePix; //traz
    @Column
    private Integer tipoChavePix; //traz
    @Enumerated(EnumType.STRING)
    @Column
    private Status status; //traz // Forma de alterar o status
    @Column
    private String url; //traz!!!
}
