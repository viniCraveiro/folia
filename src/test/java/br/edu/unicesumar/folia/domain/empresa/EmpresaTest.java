package br.edu.unicesumar.folia.domain.empresa;

import br.edu.unicesumar.folia.domain.endereco.Endereco;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;


public class EmpresaTest {

    @InjectMocks
    private  EmpresaService empresaService;

    @Mock
    private EmpresaRepository empresaRepository;

    private Endereco endereco;
    private Empresa empresa;

    @BeforeEach
    void serUp(){
        MockitoAnnotations.openMocks(this);
        endereco = new Endereco ("Avenida teste", "123", "Apto 101", "rua teste", "Maringa", "01311-200", "pr");
        empresa = new Empresa("nome fantasia teste", "razao social", "19.722.707/0001-69", "empresa@exple.com", "44 984387000",endereco);
    }

    @Test
    void deveSalvarEmpresa(){
        when(empresaRepository.save(any(Empresa.class))).thenReturn(empresa);
        boolean resultado = (empresaService.salvaEmpresa(empresa) != null);
        Assertions.assertTrue(resultado, "Empresa deve ser salva");
        verify(empresaRepository).save(empresa);
    }

    @Test
    void deveAtualizarNomeFantasiaDaEmpresa(){
        when(empresaRepository.findById(empresa.getId())).thenReturn(Optional.of(empresa));
        when(empresaRepository.save(any(Empresa.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Endereco enderecoAtualizado = new Endereco("Avenida Teste Atualizado", "321", "Apto 232", "Rua teste Atualizado", "Maringá", "01311-200", "PR");
        Empresa empresaAtualizado = new Empresa("Nome Fantasia Atualizado", "Razão Social Atualizada", "19.722.707/0001-69", "empresa_atualizada@example.com", "senha12345", enderecoAtualizado);

        Empresa resultado = empresaService.atualizaEmpresa(empresa.getId(),empresaAtualizado);

        Assertions.assertEquals("Nome Fantasia Atualizado", resultado.getNomeFantasia(), "O Nome fantasia deve ser atualizado" );
        Assertions.assertEquals("Avenida Teste Atualizado", resultado.getEndereco().getLogradouro(),"Endereço da empresa deve ser atualizado");
        verify(empresaRepository).save(empresa);

    }

    @Test
    void deveDeletarEmpresa(){
        when(empresaRepository.findById(empresa.getId())).thenReturn(Optional.of(empresa));

        empresaService.deletaEmpresa(empresa.getId());
        verify(empresaRepository).delete(empresa);
    }



}
