package br.edu.unicesumar.folia.domain.usuario;

import br.edu.unicesumar.folia.domain.endereco.Endereco;

public class UsuarioDataProvider {

    public static Usuario luiz() {
        Endereco enderecoNovo = new Endereco("Avenida Teste Atualizado", "321", "Apto 232", "Rua teste Atualizado", "Maringá", "01311-200", "PR");

        return new Usuario("453.263.940-90", "Luiz", "luiz@example.com", "luiz.gabriel", "1234", TipoUsuario.ADMIN, enderecoNovo);
    }
}
