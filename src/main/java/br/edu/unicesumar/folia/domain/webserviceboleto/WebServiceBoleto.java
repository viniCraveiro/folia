package br.edu.unicesumar.folia.domain.webserviceboleto;

import br.edu.unicesumar.folia.domain.Entidade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "WEB_SERVICE_BOLETO")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WebServiceBoleto extends Entidade {

    @Column
    private String pathGravarRegistro;
    @Column
    private int ambiente;
    @Column
    private int operacao;
    @Column
    private String versaoDf;
    @Column
    private int userCertificateHttp;
    @Column
    private int sslType;
    @Column
    private int timeout;
    @Column
    private String arquivoCrt;
    @Column
    private String arquivoKey;
    @Column
    private int logNivel;
    @Column
    private String caminhoGravarRegistro;
    @Column
    private String nomeArquivoLog;

}
