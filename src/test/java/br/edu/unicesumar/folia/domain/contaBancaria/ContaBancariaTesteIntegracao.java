package br.edu.unicesumar.folia.domain.contaBancaria;

import br.edu.unicesumar.folia.FoliaApplication;
import br.edu.unicesumar.folia.domain.banco.Banco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.Assertions;

import java.util.UUID;

@SpringBootTest(classes = FoliaApplication.class)
@Transactional
@Rollback
public class ContaBancariaTesteIntegracao {

    @Autowired
    private ContaBancariaService contaBancariaService;

    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    private Banco banco;
    private ContaBancaria contaBancaria;
    private UUID idConta;

    @BeforeEach
    void setUp() {
        banco = new Banco("Banco Teste");
        contaBancaria = new ContaBancaria(banco, "12345", "6789", "Titular Teste", "123.456.789-00", TipoConta.CORRENTE, "BR123456789", "01/01/2023", "4499999999", "titular@teste.com");
    }

    @Test
    void deveSalvarContaBancariaNoBanco() {
        ContaBancaria contaSalva = contaBancariaService.cadastrarContaBancaria(contaBancaria);

        Assertions.assertNotNull(contaSalva, "A conta bancária deve ser salva!");
        ContaBancaria contaEncontrada = contaBancariaRepository.findById(contaSalva.getId()).orElse(null);
        Assertions.assertNotNull(contaEncontrada, "A conta bancária deve estar presente no banco de dados!");
        Assertions.assertEquals("12345", contaEncontrada.getNumeroConta());
        Assertions.assertEquals("Banco Teste", contaEncontrada.getBanco().getNome());
    }
}
