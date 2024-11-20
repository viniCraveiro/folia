package br.edu.unicesumar.folia.domain.empresa;

import br.edu.unicesumar.folia.domain.Entidade;
import br.edu.unicesumar.folia.domain.endereco.Endereco;
import br.edu.unicesumar.folia.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Table(name = "EMPRESA")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Empresa extends Entidade {

        @Column(nullable = false)
        private String nomeFantasia;

        @Column(nullable = false)
        private String razaoSocial;

        @Column(nullable = false, unique = true)
       // @Pattern(regexp = "\\d{14}", message = "O CNPJ deve conter exatamente 14 dígitos.")
        private String cnpj;

        @Column(nullable = false)
        private String email;

        @Column(nullable = false)
        private String telefone;

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "ENDERECO_FK")
        private Endereco endereco;

        @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
        private Set<Usuario> usuarios = new HashSet<>();

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

        public void addUsuario(Usuario usuario) {
                usuarios.add(usuario);
                usuario.setEmpresa(this);
        }
}
