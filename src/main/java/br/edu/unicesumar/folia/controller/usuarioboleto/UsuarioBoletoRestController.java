package br.edu.unicesumar.folia.controller.usuarioboleto;

import br.edu.unicesumar.folia.domain.usuarioboleto.UsuarioBoletoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
