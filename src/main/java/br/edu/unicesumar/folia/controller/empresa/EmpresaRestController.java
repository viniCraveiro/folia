package br.edu.unicesumar.folia.controller.empresa;

import br.edu.unicesumar.folia.domain.empresa.Empresa;
import br.edu.unicesumar.folia.domain.empresa.EmpresaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import br.edu.unicesumar.folia.controller.empresa.EmpresaDTO;

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
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid EmpresaDTO empresaDTO) {
        Empresa empresa = convertToEntity(empresaDTO);
        empresaService.salvaEmpresa(empresa);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<EmpresaDTO> atualizar(@PathVariable UUID uuid, @RequestBody EmpresaDTO empresaDTO) {
        Empresa empresaAtualizada = empresaService.atualizaEmpresa(uuid, convertToEntity(empresaDTO));
        EmpresaDTO responseDTO = convertToDTO(empresaAtualizada);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


   private Empresa convertToEntity(EmpresaDTO dto) {
        return new Empresa(
                dto.getNomeFantasia(),
                dto.getCnpj(),
                dto.getEmail(),
                dto.getTelefone(),
                dto.getEndereco()
        );
    }

    private EmpresaDTO convertToDTO(Empresa empresa) {
        return new EmpresaDTO(
                empresa.getNomeFantasia(),
                empresa.getCnpj(),
                empresa.getEmail(),
                empresa.getTelefone(),
                empresa.getEndereco()
        );
    }


}

