package br.edu.unicesumar.folia.domain.configuracaobanco;

import br.edu.unicesumar.folia.domain.Entidade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "CONFIGURACAO_BANCO")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfiguracaoBanco extends Entidade {

    @Column
    private int tipoCobranca;
    @Column
    private int numeroCorrespondente;
    @Column
    private int layoutVersaoArquivo;
    @Column
    private int layoutVersaoLote;
    @Column
    private String localPagamento;
    @Column
    private String orientacaoBanco;
    @Column
    private int digito;
    @Column
    private String casasDecimaisMoraJuros;
    @Column
    private String cip;

}
