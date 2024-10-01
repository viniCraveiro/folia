package br.edu.unicesumar.folia.controller.empresa;

import br.edu.unicesumar.folia.domain.endereco.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmpresaDTO {
    private String nomeFantasia;
    private String cnpj;
    private String email;
    private String telefone;
    private Endereco endereco;

    public EmpresaDTO(String nomeFantasia, String cnpj, String email, String telefone, Endereco endereco) {
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }
}
