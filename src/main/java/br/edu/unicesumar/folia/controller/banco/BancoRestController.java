package br.edu.unicesumar.folia.controller.banco;

import br.edu.unicesumar.folia.domain.banco.Banco;
import br.edu.unicesumar.folia.domain.banco.BancoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(
        name = "Banco",
        description = "CRUD REST - create Banco, Update Banco, Get Banco, Get All Banco, Delete Banco"
)
@RestController
@RequestMapping("api/banco")
public class BancoRestController {

    private final BancoService bancoService;

    public BancoRestController(BancoService bancoService) {
        this.bancoService = bancoService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> create(@RequestBody @Valid Banco banco){
        try{ bancoService.salvaBanco(banco);
        return new ResponseEntity<>(null, HttpStatus.CREATED); }
        catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid){
        try{bancoService.deletarBanco(uuid);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);}
        catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Banco> update(@PathVariable UUID uuid, @RequestBody Banco banco){
        try{bancoService.atualizaBanco(uuid, banco);
        return new ResponseEntity<>(banco, HttpStatus.OK);}
        catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Banco> getAll(@PathVariable UUID uuid){
        try{Banco response = bancoService.encontrarPorIdBanco(uuid);
        return new ResponseEntity<>(response, HttpStatus.OK);}
        catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
