package br.edu.unicesumar.folia.domain.arquivoBoleto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Table(name = "ARQUIVOBOLETO")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArquivoBoleto {

    @Id
    @Setter(AccessLevel.NONE)
    private UUID uuid;
    @Column
    private String nome;
    @Column
    private String diretorioLogo;
    @Column
    private String filtro;
    @Column
    private String layout;
    @Column
    private String mostrarPreview;
    @Column
    private String mostrarProgresso;
    @Column
    private String mostrarSetup;
    @Column
    private String numeroCopias;
    @Column
    private String printerNome;
    @Column
    private String softwareHouse;
    @Column
    private String alterarEscalaPadrao;
    @Column
    private String novaEscala;
    @Column
    private String calcularNomeArquivoPdfIndividual;
    @Column
    private String margemSuperior;
    @Column
    private String margemInferior;
    @Column
    private String margemEsquerda;
    @Column
    private String margemDireita;

}
