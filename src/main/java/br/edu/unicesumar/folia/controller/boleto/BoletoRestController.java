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

import java.util.UUID;

@Tag(
        name = "Boleto",
        description = "CRUD REST - create Boleto, Update Boleto, Get Boleto, Get All Boleto, Delete Boleto"
)
@RestController
@RequestMapping("api/boleto")
public class BoletoRestController {

    private final BoletoService boletoService;

    public BoletoRestController(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> create(@RequestBody @Valid Boleto boleto) {
        try {
            boletoService.criarBolete(boleto);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        try {
            boletoService.deletarBoleto(uuid);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Boleto> update(@PathVariable UUID uuid, @RequestBody Boleto boleto) {
        try {
            Boleto atualizado = boletoService.atualizarBolete(uuid, boleto);
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Boleto> getById(@PathVariable UUID uuid) {
        try {
            Boleto boleto = boletoService.findById(uuid);
            return new ResponseEntity<>(boleto, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

