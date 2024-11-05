package br.edu.unicesumar.folia.controller.usuarioboleto;

import br.edu.unicesumar.folia.domain.boleto.Boleto;
import br.edu.unicesumar.folia.domain.boleto.BoletoService;
import br.edu.unicesumar.folia.domain.usuarioboleto.UsuarioBoletoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(
        name = "UsuarioBoleto",

        description = "CRUD REST - create UsuarioBoleto, Update UsuarioBoleto, Delete UsuarioBoleto"

)
@RestController
@RequestMapping("api/usuarioBoleto")
public class UsuarioBoletoRestController {

    @Autowired
    private UsuarioBoletoService usuarioBoletoService;

    @GetMapping("/{uuid}")
    public ResponseEntity<UsuarioBoletoListaDTO> obterBoletosPorUsuario(@PathVariable UUID uuid) {
        UsuarioBoletoListaDTO dto = usuarioBoletoService.obterDadosUsuarioBoletos(uuid);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/filtros")
    public ResponseEntity<List<Boleto>> obterFiltro(@RequestBody UsuarioBoletoFiltroDTO filtro) {
        List<Boleto> boletosFiltrados = usuarioBoletoService.filtrarBoletos(filtro);
        return ResponseEntity.ok(boletosFiltrados);
    }

}
