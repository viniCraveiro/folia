package br.edu.unicesumar.folia.domain.usuario;

import br.edu.unicesumar.folia.domain.endereco.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Assertions;

import java.util.Optional;

public class UsuarioTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    private Endereco endereco;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        endereco = new Endereco("Avenida Teste", "123", "Apto 101", "Rua teste", "Maringá", "01311-200", "PR");
        usuario = new Usuario("13880494908", "Gabriel", "gabriel@example.com", endereco);
    }

    @Test
    void deveSalvarUsuario() {
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        boolean resultado = (usuarioService.salvaUsuario(usuario) != null);

        Assertions.assertTrue(resultado, "Usuário deve ser salvo!");
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void deveAtualizarNomeDoUsuario() {
        when(usuarioRepository.findById(usuario.getId())).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Endereco enderecoNovo = new Endereco("Avenida Teste Atualizado", "321", "Apto 232", "Rua teste Atualizado", "Maringá", "01311-200", "PR");
        Usuario usuarioAtualizado = new Usuario("453.263.940-90", "Luiz", "luiz@example.com", enderecoNovo);

        Usuario resultado = usuarioService.atualizaUsuario(usuario.getId(), usuarioAtualizado);

        Assertions.assertEquals("Luiz", resultado.getNome(), "Nome do usuário deve ser atualizado");
        Assertions.assertEquals("Avenida Teste Atualizado", resultado.getEndereco().getLogradouro(), "Endereço do usuário deve ser atualizado");
        verify(usuarioRepository).save(usuario);
    }

}
