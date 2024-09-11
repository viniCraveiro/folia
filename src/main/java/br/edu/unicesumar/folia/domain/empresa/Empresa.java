package br.edu.unicesumar.folia.domain.empresa;

import br.edu.unicesumar.folia.domain.Entidade;
import br.edu.unicesumar.folia.domain.endereco.Endereco;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Table(name = "EMPRESA")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Empresa extends Entidade {

        @Column(nullable = false)
        @NotBlank(message = "O nome fantasia não pode estar em branco.")
        private String nomeFantasia;

        @Column(nullable = false)
        @NotBlank(message = "A razão social não pode estar em branco.")
        private String razaoSocial;

        @Column(nullable = false, unique = true)
        @NotBlank(message = "O CNPJ não pode estar em branco.")
        @Pattern(regexp = "\\d{14}", message = "O CNPJ deve conter exatamente 14 dígitos.")
        private String cnpj;

        @Column(nullable = false)
        @NotBlank(message = "O email não pode estar em branco.")
        @Email(message = "O email deve ser válido.")
        private String email;

        @Column(nullable = false)
        @NotBlank(message = "A senha não pode estar em branco.")
        @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres.")
        private String senha;

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "ENDERECO_FK")
        @NotNull(message = "O endereço é obrigatório.")
        private Endereco endereco;

}
