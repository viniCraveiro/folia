package br.edu.unicesumar.folia.controller.boleto;

import br.edu.unicesumar.folia.domain.boleto.Boleto;
import br.edu.unicesumar.folia.domain.boleto.BoletoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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

    public BoletoRestController(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

    //todos os boletos
    @GetMapping
    public ResponseEntity<List<Boleto>> listarBoletos() {
        List<Boleto> boletos = boletoService.listarBoletos();
        return new ResponseEntity<>(boletos, HttpStatus.OK);
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
}

