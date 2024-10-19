package br.edu.unicesumar.folia.domain.usuario;

import br.edu.unicesumar.folia.FoliaApplication;
import br.edu.unicesumar.folia.domain.endereco.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.Assertions;

@SpringBootTest(classes = FoliaApplication.class)
@Transactional
@Rollback
public class UsuarioTesteIntegracao {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Endereco endereco;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        endereco = new Endereco("Avenida Teste", "123", "Apto 101", "Rua teste", "Maringá", "01311-200", "PR");
        usuario = UsuarioDataProvider.luiz();
    }

    @Test
    void deveSalvarUsuarioNoBanco() {
        Usuario usuarioSalvo = usuarioService.salvaUsuario(usuario);

        Assertions.assertNotNull(usuarioSalvo, "Usuário deve ser salvo!");
        Usuario usuarioEncontrado = usuarioRepository.findById(usuarioSalvo.getId()).orElse(null);
        Assertions.assertNotNull(usuarioEncontrado, "Usuário deve estar presente no banco de dados!");
        Assertions.assertEquals("Gabriel", usuarioEncontrado.getNome());
        Assertions.assertEquals("Avenida Teste", usuarioEncontrado.getEndereco().getLogradouro());
    }
}
