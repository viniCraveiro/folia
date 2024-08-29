package br.edu.unicesumar.folia.domain.teste.usuario;

import br.edu.unicesumar.folia.domain.endereco.Endereco;
import br.edu.unicesumar.folia.domain.usuario.Usuario;
import br.edu.unicesumar.folia.domain.usuario.UsuarioRepository;
import br.edu.unicesumar.folia.domain.usuario.UsuarioService;
import br.edu.unicesumar.folia.domain.usuario.ValidarIdentificacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.UUID;

public class TesteUsuario {

    @InjectMocks
    private UsuarioService usuarioService;
    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testarCadastroValido(){

        Usuario usuario = new Usuario();
        usuario.setIdentificacao("13880494908");
        usuario.setNome("Gabriel");
        usuario.setEmail("gabriel@gmail.com");

        // Simula a operação de salvar o usuário
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        // Chama o metodo a ser testado
        boolean resultado = (usuarioService.salvaUsuario(usuario) != null);
        // Verifica se o resultado é verdadeiro
        Assertions.assertTrue(resultado,"Usuario dever ser salvo!");
        // Verifica se o metodo save do repositório foi chamado
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void testarCadastroValidoComCPF(){
        Usuario usuario = new Usuario();
        usuario.setIdentificacao("13880494908"); // CPF válido para o exemplo
        usuario.setNome("Gabriel");
        usuario.setEmail("gabriel@gmail.com");

        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        boolean resultado = (usuarioService.salvaUsuario(usuario) != null);

        Assertions.assertTrue(resultado, "Usuário deveria ser salvo com CPF válido!");
        verify(usuarioRepository).save(usuario);
    }
}
