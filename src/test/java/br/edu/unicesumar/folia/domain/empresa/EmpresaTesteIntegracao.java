package br.edu.unicesumar.folia.domain.empresa;


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
public class EmpresaTesteIntegracao {


    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private EmpresaRepository empresaRepository;

    private Endereco endereco;
    private Empresa empresa;

    @BeforeEach
    void setUp() {
        // Criando um endereço de teste
        endereco = new Endereco("Avenida Teste", "123", "Apto 101", "Rua Teste", "Maringá", "01311-200", "PR");

        // Criando uma empresa de teste
        empresa = new Empresa(
                "Nome Fantasia Teste",
                "Razão Social Teste",
                "12345678000195",
                "empresa@example.com",
                "44984387000",
                endereco
        );
    }

    @Test
    void deveSalvarEmpresaNoBanco() {
        Empresa empresaSalva = empresaService.salvaEmpresa(empresa);

       Assertions.assertNotNull(empresaSalva, "A empresa deve ser salva!");


        Empresa empresaEncontrada = empresaRepository.findById(empresaSalva.getId()).orElse(null);

        Assertions.assertNotNull(empresaEncontrada, "A empresa deve estar presente no banco de dados!");

        Assertions.assertEquals("Nome Fantasia Teste", empresaEncontrada.getNomeFantasia());
        Assertions.assertEquals("Avenida Teste", empresaEncontrada.getEndereco().getLogradouro());
        Assertions.assertEquals("12345678000195", empresaEncontrada.getCnpj());
    }
}

