package br.edu.unicesumar.folia.domain.webServiceBoleto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Table(name = "WEBSERVICEBOLETO")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WebServiceBoleto {

    @Id
    @Setter(AccessLevel.NONE)
    private UUID uuid;
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
