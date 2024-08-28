package br.edu.unicesumar.folia.domain.diretorioBoleto;

import br.edu.unicesumar.folia.domain.Entidade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Table(name = "DIRETORIOBBOLETO")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiretorioBoleto extends Entidade {

    @Column
    private int layoutRemessa;
    @Column
    private String diretorioArquivoRemessa;
    @Column
    private String diretorioArquivoRetorno;
    @Column
    private String nomeArquivoRemessa;
    @Column
    private String nomeArquivoRetorno;
    @Column
    private int numeroArquivo;
    @Column
    private String dataArquivo;
    @Column
    private String dataCreditoLancamento;
    @Column
    private int imprimirMensagemPadrao;
    @Column
    private Boolean leCedenteRetorno;
    @Column
    private int removeAcentosArquivoRemessa;
    @Column
    private String prefixoArquivoRemessa;

}
