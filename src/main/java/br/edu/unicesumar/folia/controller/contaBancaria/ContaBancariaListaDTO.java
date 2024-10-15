package br.edu.unicesumar.folia.controller.contaBancaria;

import br.edu.unicesumar.folia.domain.banco.Banco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContaBancariaListaDTO {
    private Banco banco;
    private String titular;
    private String numeroConta;
    private String codigoIban;


    public void ContaBancariaDTO(Banco banco, String titular, String numeroConta, String codigoIban){
        this.banco = banco;
        this.titular = titular;
        this.numeroConta = numeroConta;
        this.codigoIban = codigoIban;

    }

}
