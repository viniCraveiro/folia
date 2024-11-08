package br.edu.unicesumar.folia.domain.endereco;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EnderecoTest {

    @InjectMocks
    private EnderecoService enderecoService;

    @Mock
    private EnderecoRepository enderecoRepository;

    private Endereco endereco;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        endereco = new Endereco("Rua Exemplo", "123", "Apto 101", "Bairro Exemplo", "Cidade Exemplo", "00000-000", "SP");
    }

    @Test
    void deveSalvarEndereco() {
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(endereco);

        boolean resultado = (enderecoService.salvaEndereco(endereco) != null);

        Assertions.assertTrue(resultado, "Endereço deve ser salvo!");
        verify(enderecoRepository).save(endereco);
    }

    @Test
    void deveDeletarEndereco() {
        UUID uuid = UUID.randomUUID();
        when(enderecoRepository.findById(uuid)).thenReturn(Optional.of(endereco));

        enderecoService.deletaEndereco(uuid);

        verify(enderecoRepository).findById(uuid);
        verify(enderecoRepository).delete(endereco);
    }

    @Test
    void deveAtualizarEndereco() {
        UUID uuid = UUID.randomUUID();
        Endereco enderecoAtualizado = new Endereco("Rua Nova", "456", "Casa 202", "Bairro Novo", "Cidade Nova", "11111-111", "RJ");
        when(enderecoRepository.findById(uuid)).thenReturn(Optional.of(endereco));
        when(enderecoRepository.save(any(Endereco.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Endereco resultado = enderecoService.atualizaEndereco(uuid, enderecoAtualizado);

        Assertions.assertEquals("Rua Nova", resultado.getLogradouro(), "Logradouro do endereço deve ser atualizado");
        Assertions.assertEquals("456", resultado.getNumeroResidencial(), "Número residencial deve ser atualizado");
        Assertions.assertEquals("Casa 202", resultado.getComplemento(), "Complemento deve ser atualizado");
        Assertions.assertEquals("Bairro Novo", resultado.getBairro(), "Bairro deve ser atualizado");
        Assertions.assertEquals("Cidade Nova", resultado.getCidade(), "Cidade deve ser atualizada");
        Assertions.assertEquals("11111-111", resultado.getCep(), "CEP deve ser atualizado");
        Assertions.assertEquals("RJ", resultado.getUf(), "UF deve ser atualizada");

        verify(enderecoRepository).findById(uuid);
        verify(enderecoRepository).save(endereco);
    }
}
