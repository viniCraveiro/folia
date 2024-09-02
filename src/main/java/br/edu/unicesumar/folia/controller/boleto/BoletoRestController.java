package br.edu.unicesumar.folia.controller.boleto;

import br.edu.unicesumar.folia.domain.banco.Banco;
import br.edu.unicesumar.folia.domain.banco.BancoService;
import br.edu.unicesumar.folia.domain.usuario.Usuario;
import br.edu.unicesumar.folia.domain.usuario.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("boleto")
public class BoletoRestController {


    private final BancoService bancoService;

    public BoletoRestController(BancoService bancoService) {
        this.bancoService = bancoService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid Banco banco){
        bancoService.salvaBanco(banco);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deletar(@PathVariable UUID uuid){
        bancoService.deletarBanco(uuid);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Banco> atualizar(@PathVariable UUID uuid, @RequestBody Banco banco){
        bancoService.atualizaBanco(uuid, banco);
        return new ResponseEntity<>(banco, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Banco> visualizar(@PathVariable UUID uuid){
        Banco response = bancoService.encontrarPorIdBanco(uuid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
