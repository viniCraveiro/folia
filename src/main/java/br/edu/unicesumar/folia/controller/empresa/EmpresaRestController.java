package br.edu.unicesumar.folia.controller.empresa;

import br.edu.unicesumar.folia.domain.empresa.Empresa;
import br.edu.unicesumar.folia.domain.empresa.EmpresaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(
        name = "Empresa",
        description = "CRUD REST - create Empresa, Update Empresa, Delete Empresa"
)
@RestController
@RequestMapping("api/empresa")
public class EmpresaRestController {

    private final EmpresaService empresaService;

    public EmpresaRestController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid Empresa empresa){
        empresaService.salvaEmpresa(empresa);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deletar(@PathVariable UUID uuid){
        empresaService.deletaEmpresa(uuid);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Empresa> atualizar(@PathVariable UUID uuid, @RequestBody Empresa empresa){
        empresaService.atualizaEmpresa(uuid, empresa);
        return  new ResponseEntity<>(empresa, HttpStatus.OK);
    }

}

