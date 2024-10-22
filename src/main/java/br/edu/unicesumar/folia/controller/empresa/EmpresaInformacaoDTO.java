package br.edu.unicesumar.folia.controller.empresa;

import br.edu.unicesumar.folia.domain.endereco.Endereco;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class EmpresaInformacaoDTO {
    private String nome;
    private UUID uuid;


    public EmpresaInformacaoDTO(String nomeFantasia, UUID uuid) {
        this.nome = nomeFantasia;
        this.uuid = uuid;
    }
}
