package br.edu.unicesumar.folia.domain.contaBancaria;


import br.edu.unicesumar.folia.domain.Entidade;
import br.edu.unicesumar.folia.domain.banco.Banco;
import br.edu.unicesumar.folia.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "CONTA_BANCARIA")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContaBancaria extends Entidade {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BANCO_FK", nullable = false)
    private Banco banco;

    @Column(nullable = false)
    private String numeroConta;

    @Column(nullable = false)
    private String agencia;

    @Column(nullable = false)
    private String titular;

    @Column(nullable = false)
    private String cpfCnpj;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoConta tipoConta;

    @Column(nullable = false)
    private String codigoIban;

    @Column(nullable = false)
    private String dataAbertura;

    @Column
    private String telefoneContato;

    @Column
    private String emailTitular;
}