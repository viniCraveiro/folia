package br.edu.unicesumar.folia.domain.boleto;

import br.edu.unicesumar.folia.domain.banco.Banco;
import br.edu.unicesumar.folia.domain.empresa.Empresa;
import br.edu.unicesumar.folia.domain.usuario.Usuario;
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
    private Banco banco; //Agencia, agencia digito, conta, conta digito, digito verificador agencia conta
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
    private Usuario usuario; //Tipo inscricao
    @Column
    private int caracteristicaTitulo;
    @Column
    private int codigoNegativacao;
    @Column
    private Empresa dadosEmpresa; //Armazena CNPJ, nome, logradouro, numero residencial, complemento, bairro, cidade, cep, uf, telefone.
    @Column
    private int identificacaoDistribuicao;
    @Column
    private String operacao;
    @Column
    private String chavePix;
    @Column
    private int tipoChavePix;

}
