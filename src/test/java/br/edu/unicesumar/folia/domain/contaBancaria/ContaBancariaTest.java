package br.edu.unicesumar.folia.domain.contaBancaria;

import br.edu.unicesumar.folia.domain.banco.Banco;
import br.edu.unicesumar.folia.controller.contaBancaria.ContaBancariaListaDTO;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ContaBancariaTest {

    @InjectMocks
    private ContaBancariaService contaBancariaService;

    @Mock
    private ContaBancariaRepository contaBancariaRepository;

    private ContaBancaria contaBancaria;
    private UUID idConta;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Banco banco = new Banco("Banco Teste");
        contaBancaria = new ContaBancaria(banco, "12345", "6789", "Titular Teste", "123.456.789-00", TipoConta.CORRENTE, "BR123456789", "01/01/2023", "4499999999", "titular@teste.com");
        idConta = UUID.randomUUID();
    }

    @Test
    public void deveCadastrarContaBancaria() {
        when(contaBancariaRepository.save(any(ContaBancaria.class))).thenReturn(contaBancaria);

        ContaBancaria novaConta = contaBancariaService.cadastrarContaBancaria(contaBancaria);

        Assertions.assertNotNull(novaConta, "A conta bancária deve ser cadastrada.");
        verify(contaBancariaRepository).save(contaBancaria);
    }

    @Test
    public void deveListarContasBancarias() {
        List<ContaBancaria> contas = List.of(contaBancaria);
        when(contaBancariaRepository.findAll()).thenReturn(contas);

        List<ContaBancariaListaDTO> resultado = contaBancariaService.listarTodas();

        Assertions.assertFalse(resultado.isEmpty(), "A lista de contas bancárias não deve estar vazia.");
        Assertions.assertEquals(1, resultado.size(), "A lista deve conter uma conta bancária.");
        verify(contaBancariaRepository).findAll();
    }

    @Test
    public void deveBuscarContaBancariaPorId() {
        when(contaBancariaRepository.findById(idConta)).thenReturn(Optional.of(contaBancaria));

        Optional<ContaBancaria> contaEncontrada = contaBancariaService.buscarPorId(idConta);

        Assertions.assertTrue(contaEncontrada.isPresent(), "A conta bancária deve ser encontrada.");
        Assertions.assertEquals("12345", contaEncontrada.get().getNumeroConta(), "O número da conta deve corresponder.");
        verify(contaBancariaRepository).findById(idConta);
    }

    @Test
    public void deveAtualizarContaBancaria() {
        when(contaBancariaRepository.findById(idConta)).thenReturn(Optional.of(contaBancaria));
        when(contaBancariaRepository.save(any(ContaBancaria.class))).thenReturn(contaBancaria);

        ContaBancaria contaAtualizada = new ContaBancaria(contaBancaria.getBanco(), "54321", "9876", "Titular Atualizado", "987.654.321-00", TipoConta.POUPANCA, "BR987654321", "02/02/2023", "4498888888", "titular@atualizado.com");

        ContaBancaria resultado = contaBancariaService.atualizarConta(idConta, contaAtualizada);

        Assertions.assertEquals("54321", resultado.getNumeroConta(), "O número da conta deve ser atualizado.");
        Assertions.assertEquals("Titular Atualizado", resultado.getTitular(), "O titular deve ser atualizado.");
        verify(contaBancariaRepository).save(contaBancaria);
    }

    @Test
    public void deveDeletarContaBancaria() {
        when(contaBancariaRepository.findById(idConta)).thenReturn(Optional.of(contaBancaria));

        contaBancariaService.detelarContaBancaria(idConta);

        verify(contaBancariaRepository).delete(contaBancaria);
    }

    @Test
    public void deveLancarExcecaoQuandoContaNaoEncontradaAoDeletar() {
        when(contaBancariaRepository.findById(idConta)).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> contaBancariaService.detelarContaBancaria(idConta));

        verify(contaBancariaRepository, never()).delete(any());
    }
}
