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
public class Empresa extends Entidade {

        @Column(nullable = false)
        @NotBlank(message = "O nome fantasia não pode estar em branco.")
        private String nomeFantasia;

        @Column(nullable = false)
        @NotBlank(message = "A razão social não pode estar em branco.")
        private String razaoSocial;

        @Column(nullable = false, unique = true)
        @NotBlank(message = "O CNPJ não pode estar em branco.")
       // @Pattern(regexp = "\\d{14}", message = "O CNPJ deve conter exatamente 14 dígitos.")
        private String cnpj;

        @Column(nullable = false)
        @NotBlank(message = "O email não pode estar em branco.")
        @Email(message = "O email deve ser válido.")
        private String email;

        @Column(nullable = false)
        @NotNull(message = "o relefone não pode estar em branco")
        private String telefone;

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "ENDERECO_FK")
        @NotNull(message = "O endereço é obrigatório.")
        private Endereco endereco;

        public Empresa(String nomeFantasia, String razaoSocial, String cnpj, String email, String telefone, Endereco endereco) {
                this.nomeFantasia = nomeFantasia;
                this.razaoSocial = razaoSocial;
                this.cnpj = cnpj;
                this.email = email;
                this.telefone = telefone;
                this.endereco = endereco;
        }

        public Empresa(String nomeFantasia, String cnpj, String email, String telefone, Endereco endereco) {
                super();

        }
}
