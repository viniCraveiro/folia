package br.edu.unicesumar.folia.controller.usuarioboleto;

import br.edu.unicesumar.folia.domain.usuarioboleto.UsuarioBoletoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/empresa/{empresaUuid}")
    public ResponseEntity<List<UsuarioBoletoListaDTO>> obterBoletosPorEmpresa(@PathVariable UUID empresaUuid) {
        List<UsuarioBoletoListaDTO> dtos = usuarioBoletoService.obterDadosUsuariosPorEmpresa(empresaUuid);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/ultimosBoletos/{empresaUuid}")
    public ResponseEntity<List<UsuarioBoletoListaDTO>> obterUltimosBoletos(@PathVariable UUID empresaUuid) {
        List<UsuarioBoletoListaDTO> dtos = usuarioBoletoService.obterUltimosCincoUsuariosPorBoletos(empresaUuid);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/filtrar")
    public ResponseEntity<List<UsuarioBoletoFiltradoDTO>> filtrarBoletos(@RequestBody UsuarioBoletoFiltroDTO filtro) {
        return ResponseEntity.ok(usuarioBoletoService.buscarComFiltro(filtro));
    }

}
