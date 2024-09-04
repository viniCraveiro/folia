package br.edu.unicesumar.folia.controller.endereco;

import br.edu.unicesumar.folia.domain.endereco.Endereco;
import br.edu.unicesumar.folia.domain.endereco.EnderecoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(
        name = "Endereco",
        description = "CRUD REST - create Endereco, Update Endereco, Delete Endereco"
)
@RestController
@RequestMapping("endereco")
public class EnderecoRestController {

    private final EnderecoService enderecoService;

    public EnderecoRestController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid Endereco endereco){
        enderecoService.salvaEndereco(endereco);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deletar(@PathVariable UUID uuid){
        enderecoService.deletaEndereco(uuid);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Endereco> atualizar(@PathVariable UUID uuid, @RequestBody Endereco endereco){
        enderecoService.atualizaEndereco(uuid, endereco);
        return  new ResponseEntity<>(endereco, HttpStatus.OK);
    }

}
