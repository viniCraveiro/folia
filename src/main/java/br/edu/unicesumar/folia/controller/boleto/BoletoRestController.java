package br.edu.unicesumar.folia.controller.boleto;

import br.edu.unicesumar.folia.domain.boleto.Boleto;
import br.edu.unicesumar.folia.domain.boleto.BoletoRepository;
import br.edu.unicesumar.folia.domain.boleto.BoletoService;
import br.edu.unicesumar.folia.domain.usuario.TipoUsuario;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(
        name = "Boleto",
        description = "Listar, buscar e atualizar status de boletos"
)
@RestController
@RequestMapping("api/boleto")
public class BoletoRestController {

    private final BoletoService boletoService;

    private final BoletoRepository repository;

    public BoletoRestController(BoletoService boletoService, BoletoRepository repository) {
        this.boletoService = boletoService;
        this.repository = repository;
    }

    //todos os boletos
    @GetMapping
    public ResponseEntity<List<Boleto>> listarBoletos() {
        List<Boleto> boletos = boletoService.listarBoletos();
        return new ResponseEntity<>(boletos, HttpStatus.OK);
    }

    @GetMapping("/usuario/{uuid}")
    public ResponseEntity<Page<BoletoListaDTO>> listarBoletosPorUsuario(@PathVariable UUID uuid, @PageableDefault(size = 10) Pageable pageable) {
        Page<BoletoListaDTO> boletos = boletoService.listarBoletosPorUsuario(uuid, pageable);
        return ResponseEntity.ok(boletos);
    }
    // Buscar boleto
    @GetMapping("/{uuid}")
    public ResponseEntity<Boleto> buscarBoleto(@PathVariable UUID uuid) {
        try {
            Boleto boleto = boletoService.buscarBoleto(uuid);
            return new ResponseEntity<>(boleto, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // Atualizar status boleto
    @PutMapping("/{uuid}/status")
    public ResponseEntity<Void> atualizarStatusBoleto(@PathVariable UUID uuid, @RequestParam String novoStatus) {
        try {
            boletoService.atualizarStatusBoleto(uuid, novoStatus);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/listarBoletos/{uuid}")
    public ResponseEntity<BoletoInformacoesDTO> informacoesBoletos(@PathVariable UUID uuid) {
        BoletoInformacoesDTO boletos = boletoService.listarBoletosPorEmpresa(uuid);
        return new ResponseEntity<>(boletos, HttpStatus.OK);
    }

}

