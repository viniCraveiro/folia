package br.edu.unicesumar.folia.domain.usuario;

import br.edu.unicesumar.folia.domain.empresa.Empresa;
import br.edu.unicesumar.folia.domain.endereco.Endereco;

public class UsuarioDataProvider {

    public static Usuario luiz() {
        Endereco enderecoNovo = new Endereco("Avenida Teste Atualizado", "321", "Apto 232", "Rua teste Atualizado", "Maringá", "01311-200", "PR");
        Endereco enderecoEmpresa = new Endereco("Avenida Atualizado Teste", "123", "Apto 323", "Rua Atualizado teste", "Maringá", "01312-200", "PR");
        Empresa empresaNova = new Empresa("Empresa A", "Razao Social A", "12345678000101", "contato@empresaa.com", "5544999999999",
                enderecoEmpresa);

        return new Usuario("453.263.940-90", "Luiz", "luiz@example.com", "luiz.gabriel", "Senha.1234", TipoUsuario.ADMIN, enderecoNovo, empresaNova);
    }
}
