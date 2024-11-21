package br.edu.unicesumar.folia.domain.boleto;

import br.edu.unicesumar.folia.controller.boleto.BoletoDTO;
import br.edu.unicesumar.folia.controller.boleto.BoletoInformacoesDTO;
import br.edu.unicesumar.folia.controller.boleto.BoletoListaDTO;
import br.edu.unicesumar.folia.controller.boleto.BoletoRestController;
import br.edu.unicesumar.folia.domain.banco.Banco;
import br.edu.unicesumar.folia.domain.usuario.Usuario;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class BoletoTest {

    @Mock
    private BoletoService boletoService;

    @InjectMocks
    private BoletoRestController boletoRestController;

    @BeforeEach
    public void setup() {
        // Inicializa os mocks
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarBoletos() {
        // Mock dos boletos
        List<Boleto> boletosMock = Arrays.asList(new Boleto(), new Boleto());

        // Mock do serviço
        when(boletoService.listarBoletos()).thenReturn(boletosMock);

        // Executa o método do controller
        ResponseEntity<List<Boleto>> response = boletoRestController.listarBoletos();

        // Valida o resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(boletosMock.size(), response.getBody().size());
    }

    @Test
    public void testListarBoletosPorUsuario() {
        UUID uuidMock = UUID.randomUUID();
        Pageable pageable = PageRequest.of(0, 10);

        Page<BoletoListaDTO> boletosPageMock = new PageImpl<>(Arrays.asList(new BoletoListaDTO(), new BoletoListaDTO()));

        when(boletoService.listarBoletosPorUsuario(eq(uuidMock), eq(pageable))).thenReturn(boletosPageMock);

        ResponseEntity<Page<BoletoListaDTO>> response = boletoRestController.listarBoletosPorUsuario(uuidMock, pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(boletosPageMock.getTotalElements(), response.getBody().getTotalElements());
    }

    @Test
    public void testBuscarBoletoNotFound() {
        UUID uuidMock = UUID.randomUUID();

        when(boletoService.buscarBoleto(uuidMock)).thenThrow(new EntityNotFoundException());

        ResponseEntity<BoletoDTO> response = boletoRestController.buscarBoleto(uuidMock);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAtualizarStatusBoleto() {
        UUID uuidMock = UUID.randomUUID();
        String novoStatusMock = "ABERTO";

        // Nenhuma exceção esperada
        doNothing().when(boletoService).atualizarStatusBoleto(uuidMock, novoStatusMock);

        ResponseEntity<String> response = boletoRestController.atualizarStatusBoleto(uuidMock, novoStatusMock);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testAtualizarStatusBoletoNotFound() {
        UUID uuidMock = UUID.randomUUID();
        String novoStatusMock = "ABERTO";

        doThrow(new EntityNotFoundException()).when(boletoService).atualizarStatusBoleto(uuidMock, novoStatusMock);

        ResponseEntity<String> response = boletoRestController.atualizarStatusBoleto(uuidMock, novoStatusMock);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testInformacoesBoletos() {
        UUID uuidMock = UUID.randomUUID();
        BoletoInformacoesDTO infoMock = new BoletoInformacoesDTO();

        when(boletoService.listarBoletosPorEmpresa(uuidMock)).thenReturn(infoMock);

        ResponseEntity<BoletoInformacoesDTO> response = boletoRestController.informacoesBoletos(uuidMock);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(infoMock, response.getBody());
    }



}
