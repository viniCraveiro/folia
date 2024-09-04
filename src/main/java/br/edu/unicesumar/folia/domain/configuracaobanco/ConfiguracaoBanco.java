package br.edu.unicesumar.folia.domain.configuracaobanco;

import br.edu.unicesumar.folia.domain.Entidade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "CONFIGURACAO_BANCO")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfiguracaoBanco extends Entidade {

    @Column(length = 2)
    @NotNull
    private String tipoCobranca;
    @Column(length = 2)
    @NotNull
    private String numeroCorrespondente;
    @Column(length = 4)
    @NotNull
    private String layoutVersaoArquivo;
    @Column(length = 4)
    @NotNull
    private String layoutVersaoLote;
    @Column(length = 50)
    @NotNull
    private String localPagamento;
    @Column(length = 100)
    @NotNull
    private String orientacaoBanco;
    @Column(length = 4)
    @NotNull
    private String digito;
    @Column(length = 2)
    @NotNull
    private String casasDecimaisMoraJuros;
    @Column(length = 50)
    @NotNull
    private String cip;

}
